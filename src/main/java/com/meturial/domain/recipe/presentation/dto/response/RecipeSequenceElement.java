package com.meturial.domain.recipe.presentation.dto.response;

import com.meturial.domain.recipe.domain.RecipeSequence;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class RecipeSequenceElement {
    private final UUID sequenceId;
    private final Integer sequence;
    private final String content;
    private final UUID recipeId;

    public static RecipeSequenceElement of(RecipeSequence recipeSequence) {
        return RecipeSequenceElement.builder()
                .sequenceId(recipeSequence.getId())
                .sequence(recipeSequence.getSequence())
                .content(recipeSequence.getContent())
                .recipeId(recipeSequence.getRecipe().getId())
                .build();
    }
}
