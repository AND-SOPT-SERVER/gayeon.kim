package org.sopt.diary.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import org.sopt.diary.api.dto.request.DiaryPostRequest;
import org.sopt.diary.api.dto.response.DiaryDetailResponse;
import org.sopt.diary.api.dto.response.DiaryGetResponse;
import org.sopt.diary.api.dto.response.DiaryListResponse;
import org.sopt.diary.api.dto.response.DiaryResponse;
import org.sopt.diary.api.dto.response.IdResponse;
import org.sopt.diary.domain.Category;
import org.sopt.diary.domain.DiaryEntity;
import org.sopt.diary.domain.SoptMember;
import org.sopt.diary.repository.DiaryRepository;
import org.sopt.diary.repository.MemberRepository;
import org.springframework.stereotype.Component;

@Component
public class DiaryService {
    private final DiaryRepository diaryRepository;
    private final MemberRepository memberRepository;

    public DiaryService(DiaryRepository diaryRepository, MemberRepository memberRepository) {
        this.diaryRepository = diaryRepository;
        this.memberRepository = memberRepository;
    }

    public IdResponse createDiary(final Long id, final DiaryPostRequest request) {
        final SoptMember member = findMemberById(id);
        validateLastDiaryTime(member);
        final Category category = Category.getEnumCategoryFromStringCategory(request.category());
        final DiaryEntity diary = new DiaryEntity(request.title(), request.content(), category, member);
        validateDiaryTitle(diary);
        diaryRepository.save(diary);
        return IdResponse.of(diary.getId());
    }

    public DiaryDetailResponse getDiary(final Long userId, final Long id) {
        final SoptMember member = findMemberById(userId);
        DiaryEntity diary = findDiaryById(id);
        validateUser(member, diary);
        return DiaryDetailResponse.of(findDiaryById(id));
    }

    public DiaryListResponse getDiaryList(final Long userId) {
        final SoptMember member = findMemberById(userId);
        List<DiaryGetResponse> diaries = diaryRepository
                .findTop10ByOrderByCreatedAtDesc(member)
                .stream()
                .map(diary -> new DiaryGetResponse(diary.getId(), diary.getTitle()))
                .toList();
        return DiaryListResponse.of(diaries);
    }

    public void deleteDiary(final Long userId, Long id) {
        final SoptMember member = findMemberById(userId);
        DiaryEntity diary = findDiaryById(id);
        validateUser(member, diary);
        diaryRepository.deleteById(id);
    }

    public DiaryResponse updateDiary(final Long userId, final Long id, final DiaryPostRequest request) {
        final SoptMember member = findMemberById(userId);
        final DiaryEntity diary = findDiaryById(id);
        validateUser(member, diary);
        final Category category = Category.getEnumCategoryFromStringCategory(request.category());
        validateDiaryTitle(diary);
        diary.update(request.title(), request.content(), category);
        return DiaryResponse.of(diary);
    }

    public DiaryListResponse getCategoryDiaryList(final Long userId, final String requestCategory) {
        final SoptMember member = findMemberById(userId);
        Category category = Category.getEnumCategoryFromStringCategory(requestCategory);
        List<DiaryGetResponse> diaries = diaryRepository
                .findByCategoryAndMember(category, member)
                .stream()
                .map(diary -> new DiaryGetResponse(diary.getId(), diary.getTitle()))
                .toList();
        return DiaryListResponse.of(diaries);
    }

    private DiaryEntity findDiaryById(final Long id) {
         return diaryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 일기입니다."));
    }

    private void validateUser(final SoptMember member, final DiaryEntity diary) {
        if (diary.getMember() != member) {
            throw new IllegalArgumentException("권한 없음");
        }
    }

    private void validateDiaryTitle(final DiaryEntity diary) {
        if (diaryRepository.existsByTitle(diary.getTitle())) {
            throw new IllegalArgumentException("이미 존재하는 제목입니다. 다른 제목을 입력해주세요.");
        }
    }

    private void validateLastDiaryTime(final SoptMember member) {
        diaryRepository.findTopByOrderByCreatedAtDesc(member)
                .ifPresent(lastDiary -> validateDiaryTime(lastDiary.getCreatedAt()));
    }

    private void validateDiaryTime(final LocalDateTime lastCreatedAt) {
        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.between(lastCreatedAt, now);
        if (duration.toMinutes() < 5) {
            throw new IllegalStateException("마지막 일기 작성 후 5분 내에는 새로운 일기를 작성할 수 없습니다.");
        }
    }

    private SoptMember findMemberById(final Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
    }
}
