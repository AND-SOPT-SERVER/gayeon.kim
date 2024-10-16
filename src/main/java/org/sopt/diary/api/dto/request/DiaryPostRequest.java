package org.sopt.diary.api.dto.request;

public record DiaryPostRequest(
        String title,
        String content,
        String category
) {
}
