package org.sopt.diary.service;

import org.sopt.diary.api.dto.request.DiaryPostRequest;
import org.sopt.diary.api.dto.response.*;
import org.sopt.diary.domain.DiaryEntity;
import org.sopt.diary.repository.DiaryRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class DiaryService {
    private final DiaryRepository diaryRepository;

    public DiaryService(DiaryRepository diaryRepository) {
        this.diaryRepository = diaryRepository;
    }

    public DiaryIdResponse createDiary(final DiaryPostRequest request) {
        final DiaryEntity diary = new DiaryEntity(request.title(), request.content());
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
        DiaryEntity diary = findDiaryById(id);
        validateDiaryTitle(diary);
        diary.update(request.title(), request.content());
        return DiaryResponse.of(diary);
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
}
