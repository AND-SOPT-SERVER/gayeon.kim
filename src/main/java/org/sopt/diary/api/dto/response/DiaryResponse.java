package org.sopt.diary.api.dto.response;

import org.sopt.diary.domain.DiaryEntity;

public record DiaryResponse(
        String title,
        String content
) {
    public static DiaryResponse of(DiaryEntity diary) {
        return new DiaryResponse(diary.getTitle(), diary.getContent());
    }
}
