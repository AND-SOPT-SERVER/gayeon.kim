package org.sopt.diary.api.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record JoinRequest(
        @NotBlank(message = "이름을 입력해주세요.")
        String username,
        @NotBlank(message = "비밀번호를 입력해주세요.")
        String password,
        @NotBlank(message = "닉네임을 입력해주세요.")
        String nickname,
        @Min(1)
        int age
) {
}
