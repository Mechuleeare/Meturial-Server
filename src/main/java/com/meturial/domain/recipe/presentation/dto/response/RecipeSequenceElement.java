package com.meturial.domain.recipe.presentation.dto.response;

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
}
