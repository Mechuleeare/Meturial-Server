package com.meturial.domain.menu.presentation.dto.request;

import com.meturial.domain.menu.domain.type.MenuType;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@NoArgsConstructor
public class UpdateMenuRequest {

    private LocalDate date;
    private MenuType menuType;
    private UUID choiceRecipeId;
}
