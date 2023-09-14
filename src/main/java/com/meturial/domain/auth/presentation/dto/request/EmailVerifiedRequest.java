package com.meturial.domain.auth.presentation.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class EmailVerifiedRequest {

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String code;
}
