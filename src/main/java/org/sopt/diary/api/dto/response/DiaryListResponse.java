package org.sopt.diary.api.dto.response;

import java.util.List;

public record DiaryListResponse(
        List<DiaryGetResponse> diaries
) {

    public static DiaryListResponse of(List<DiaryGetResponse> diaries) {
        return new DiaryListResponse(diaries);
    }
}
