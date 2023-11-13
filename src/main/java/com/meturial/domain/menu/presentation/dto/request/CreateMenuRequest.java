package com.meturial.domain.menu.presentation.dto.request;

import com.meturial.domain.menu.domain.type.MenuType;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@NoArgsConstructor
public class CreateMenuRequest {

    @FutureOrPresent
    @NotNull(message = "YYYY-MM-DD 형식으로 날짜를 입력하여 주세요.")
    private LocalDate date;

    @NotBlank(message = "BREAKFAST(아침), LUNCH(점심), DINNER(저녁) 중 선택하여 주세요.")
    private MenuType menuType;

    @NotNull(message = "찜한 레시피 아이디를 입력하여 주세요.")
    private UUID choiceRecipeId;

    @NotNull(message = "푸쉬 알람 설정 여부를 선택하여 주세요.")
    private Boolean isActivated;
}
