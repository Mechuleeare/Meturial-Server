package com.meturial.domain.recipe.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
@Builder
public class QueryChoiceRecipeListResponse {
    private final UUID choiceId;
    private final UUID recipeId;
    private final String name;
    private final String recipeImageUrl;
    private final List<String> recipeCategory;
    private final Float starRating;
    private final Long starCount;
}
