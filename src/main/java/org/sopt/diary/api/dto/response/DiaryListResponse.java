package org.sopt.diary.api.dto.response;

import java.util.List;

public record DiaryListResponse(
        List<DiaryGetResponse> diaries
) {
}
