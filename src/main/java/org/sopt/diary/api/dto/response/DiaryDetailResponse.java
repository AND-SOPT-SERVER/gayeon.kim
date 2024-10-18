package org.sopt.diary.api.dto.response;

import org.sopt.diary.domain.DiaryEntity;

import java.time.LocalDateTime;

public record DiaryDetailResponse(
        long id,
        String title,
        String content,
        LocalDateTime createdAt
) {
    public static DiaryDetailResponse of(final DiaryEntity diary) {
        return new DiaryDetailResponse(diary.getId(), diary.getTitle(), diary.getContent(), diary.getCreatedAt());
    }
}
