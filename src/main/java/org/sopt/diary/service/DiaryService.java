package org.sopt.diary.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import org.sopt.diary.api.dto.request.DiaryCategoryRequest;
import org.sopt.diary.api.dto.request.DiaryPostRequest;
import org.sopt.diary.api.dto.response.DiaryDetailResponse;
import org.sopt.diary.api.dto.response.DiaryGetResponse;
import org.sopt.diary.api.dto.response.DiaryIdResponse;
import org.sopt.diary.api.dto.response.DiaryListResponse;
import org.sopt.diary.api.dto.response.DiaryResponse;
import org.sopt.diary.domain.Category;
import org.sopt.diary.domain.DiaryEntity;
import org.sopt.diary.repository.DiaryRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class DiaryService {
    private final DiaryRepository diaryRepository;

    public DiaryService(DiaryRepository diaryRepository) {
        this.diaryRepository = diaryRepository;
    }

    public DiaryIdResponse createDiary(final DiaryPostRequest request) {
        validateLastDiaryTime();
        final Category category = Category.getEnumCategoryFromStringCategory(request.category());
        final DiaryEntity diary = new DiaryEntity(request.title(), request.content(), category);
        validateDiaryTitle(diary);
        diaryRepository.save(diary);
        return new DiaryIdResponse(diary.getId());
    }

    @Transactional(readOnly = true)
    public DiaryDetailResponse getDiary(final Long id) {
        return DiaryDetailResponse.of(findDiaryById(id));
    }

    @Transactional(readOnly = true)
    public DiaryListResponse getDiaryList() {
        List<DiaryGetResponse> diaries = diaryRepository
                .findTop10ByOrderByCreatedAtDesc()
                .stream()
                .map(diary -> new DiaryGetResponse(diary.getId(), diary.getTitle()))
                .toList();
        return new DiaryListResponse(diaries);
    }

    public void deleteDiary(Long id) {
        diaryRepository.deleteById(id);
    }

    public DiaryResponse updateDiary(final Long id, final DiaryPostRequest request) {
        final DiaryEntity diary = findDiaryById(id);
        final Category category = Category.getEnumCategoryFromStringCategory(request.category());
        validateDiaryTitle(diary);
        diary.update(request.title(), request.content(), category);
        return DiaryResponse.of(diary);
    }

    @Transactional(readOnly = true)
    public DiaryListResponse getCategoryDiaryList(DiaryCategoryRequest request) {
        Category category = Category.getEnumCategoryFromStringCategory(request.category());
        List<DiaryGetResponse> diaries = diaryRepository
                .findByCategory(category)
                .stream()
                .map(diary -> new DiaryGetResponse(diary.getId(), diary.getTitle()))
                .toList();
        return new DiaryListResponse(diaries);
    }

    private DiaryEntity findDiaryById(final Long id) {
         return diaryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 일기입니다."));
    }

    private void validateDiaryTitle(final DiaryEntity diary) {
        if (diaryRepository.existsByTitle(diary.getTitle())) {
            throw new IllegalArgumentException("이미 존재하는 제목입니다. 다른 제목을 입력해주세요.");
        }
    }

    private void validateLastDiaryTime() {
        diaryRepository.findTopByOrderByCreatedAtDesc()
                .ifPresent(lastDiary -> validateDiaryTime(lastDiary.getCreatedAt()));
    }

    private void validateDiaryTime(LocalDateTime lastCreatedAt) {
        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.between(lastCreatedAt, now);
        if (duration.toMinutes() < 5) {
            throw new IllegalStateException("마지막 일기 작성 후 5분 내에는 새로운 일기를 작성할 수 없습니다.");
        }
    }
}
