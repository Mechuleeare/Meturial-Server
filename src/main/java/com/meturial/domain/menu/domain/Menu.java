package com.meturial.domain.menu.domain;

import com.meturial.domain.menu.domain.type.MenuType;
import com.meturial.domain.menu.exception.MenuExistException;
import com.meturial.domain.menu.exception.MenuIsNotMineException;
import com.meturial.domain.recipe.domain.ChoiceRecipe;
import com.meturial.domain.recipe.domain.Recipe;
import com.meturial.domain.user.domain.User;
import com.meturial.global.entity.BaseUUIDEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@SuperBuilder
@Entity
@Table(name = "tbl_menu")
public class Menu extends BaseUUIDEntity {

    @Column(columnDefinition = "DATE", nullable = false)
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(9)", nullable = false)
    private MenuType menuType;

    @Column(columnDefinition = "BIT(1) default 1", nullable = false)
    private Boolean isActivated;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(columnDefinition = "BINARY(16)", name = "choice_recipe_id", nullable = false)
    private ChoiceRecipe choiceRecipe;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(columnDefinition = "BINARY(16)", name = "user_id", nullable = false)
    private User user;

    public void checkMenuIsMine(UUID userId) {
        if (!this.user.getId().equals(userId)) {
            throw MenuIsNotMineException.EXCEPTION;
        }
    }

    public void checkExistSameDateAndMenuTypeAndUserId(LocalDate date, MenuType menuType, UUID userId) {
        boolean isExistSameDateAndMenuTypeAndUserId = this.date.equals(date) && this.menuType.equals(menuType) && this.user.getId().equals(userId);
        if (isExistSameDateAndMenuTypeAndUserId) {
            throw MenuExistException.EXCEPTION;
        }
    }

    public void updateMenu(ChoiceRecipe choiceRecipe, Boolean isActivated) {
        this.choiceRecipe = choiceRecipe;
        this.isActivated = isActivated;
    }

    private Recipe getMenuRecipe() {
        return this.choiceRecipe.getRecipe();
    }

    public UUID getMenuRecipeId() {
        return this.getMenuRecipe().getId();
    }

    public String getMenuRecipeName() {
        return this.getMenuRecipe().getName();
    }

    public String getMenuRecipeUrl() {
        return this.getMenuRecipe().getFoodImageUrl();
    }

    public void updateMenuNotificationActivated(Boolean isActivated) {
        this.isActivated = isActivated;
    }
}
