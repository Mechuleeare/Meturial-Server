package com.meturial.domain.notification.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NotificationSettingElement {
    private final String notificationType;
    private final Boolean isActivated;
}
