package com.meturial.domain.notification.presentation.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
public class UpdateNotificationSettingRequest {

    @NotNull
    private UUID menuId;

    @NotNull
    private Boolean isActivated;
}
