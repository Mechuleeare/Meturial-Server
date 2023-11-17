package com.meturial.domain.recipe.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class QueryRecipeStarRatingCountResponse {
    private final UUID recipeId;
    private final Float starRating;
    private final Integer starCount;
}
