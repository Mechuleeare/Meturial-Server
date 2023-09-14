package com.meturial.domain.user.presentation.dto.request;

import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Value;

@Getter
@NoArgsConstructor
public class UserSignUpRequest {

    @NotBlank(message = "이름을 입력해 주세요.")
    @Size(max = 10, message = "이름은 10자 이하여야 합니다.")
    private String name;

    @NotBlank
    @Size(min = 8, max = 60, message = "비밀번호는 8~60자여야 합니다.")
    private String password;

    @NotBlank(message = "이메일을 입력해 주세요.")
    @Email
    @Size(max = 64, message = "이메일은 64자 이하여야 합니다.")
    private String email;

    @Nullable
    private String profileImageUrl;

    @NotBlank
    @Size(max = 255)
    private String deviceToken;
}
