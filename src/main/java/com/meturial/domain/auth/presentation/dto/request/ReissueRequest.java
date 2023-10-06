package com.meturial.domain.auth.presentation.dto.request;

import lombok.Data;

@Data
public class ReissueRequest {

    private String refreshToken;
}
