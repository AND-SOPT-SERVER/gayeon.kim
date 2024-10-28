package org.sopt.diary.api.dto.response;

public record DiaryIdResponse(
        long id
) {
    public static DiaryIdResponse of(long id) {
        return new DiaryIdResponse(id);
    }
}
