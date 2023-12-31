package com.meturial.domain.recipe.domain;

import com.meturial.domain.recipe.exception.ChoiceRecipeIsNotMineException;
import com.meturial.domain.user.domain.User;
import com.meturial.global.entity.BaseUUIDEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@SuperBuilder
@Entity
@Table(name = "tbl_choice_recipe")
public class ChoiceRecipe extends BaseUUIDEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(columnDefinition = "BINARY(16)", name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(columnDefinition = "BINARY(16)", name = "recipe_id", nullable = false)
    private Recipe recipe;

    public void checkChoiceRecipeIsMine(UUID userId) {
        if (!userId.equals(this.user.getId())) {
            throw ChoiceRecipeIsNotMineException.EXCEPTION;
        }
    }

    public boolean checkIsChoice(UUID recipeId) {
        return this.recipe.getId().equals(recipeId);
    }
}
