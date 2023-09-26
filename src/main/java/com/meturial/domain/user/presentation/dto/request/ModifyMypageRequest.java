package com.meturial.domain.user.presentation.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ModifyMypageRequest {

    private String profileImageUrl;

    @NotBlank(message = "이름을 입력해 주세요.")
    @Size(max = 10, message = "이름은 10자 이하여야 합니다.")
    private String name;

    private String allergyInfo;
}
