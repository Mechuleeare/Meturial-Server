package com.meturial.domain.menu.presentation.dto.response;

import com.meturial.domain.menu.domain.Menu;
import com.meturial.domain.menu.domain.type.MenuType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MenuNotificationElement {
    private final MenuType menuType;
    private final Boolean isActivated;

    public static MenuNotificationElement of(Menu menu) {
        return new MenuNotificationElement(menu.getMenuType(), menu.getIsActivated());
    }
}
