package org.sopt.diary.api.dto.response;

public record IdResponse(
        long id
) {
    public static IdResponse of(long id) {
        return new IdResponse(id);
    }
}
