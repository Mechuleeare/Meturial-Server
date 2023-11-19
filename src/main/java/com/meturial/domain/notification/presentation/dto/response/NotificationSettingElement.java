package com.meturial.domain.notification.presentation.dto.response;

import com.meturial.domain.menu.domain.Menu;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NotificationSettingElement {
    private final String notificationType;
    private final Boolean isActivated;

    public static NotificationSettingElement of(Menu menu) {
        return new NotificationSettingElement(menu.getMenuType().toString(), menu.getIsActivated());
    }
}
