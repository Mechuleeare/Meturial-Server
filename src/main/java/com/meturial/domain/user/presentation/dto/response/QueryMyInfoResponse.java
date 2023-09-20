package com.meturial.domain.user.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class QueryMyInfoResponse {

    private final String profileImageUrl;
    private final String name;
    private final String allergyInfo;
    private final String email;
}
