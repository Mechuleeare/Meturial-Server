package com.meturial.domain.menu.presentation.dto.response;

import com.meturial.domain.menu.domain.Menu;
import com.meturial.domain.menu.domain.type.MenuType;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class MenuDetailElement {
    private final UUID menuId;
    private final UUID recipeId;
    private final String recipeName;
    private final MenuType menuType;
    private final String recipeImageUrl;

    public static MenuDetailElement of(Menu menu) {
        return MenuDetailElement.builder()
                .menuId(menu.getId())
                .recipeId(menu.getMenuRecipeId())
                .recipeName(menu.getMenuRecipeName())
                .menuType(menu.getMenuType())
                .recipeImageUrl(menu.getMenuRecipeUrl())
                .build();
    }
}
