package com.meturial.domain.recipe.presentation.dto.response;

import com.meturial.domain.recipe.domain.RecipeSequence;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
@Builder
public class QueryRecipeDetailResponse {
    private final UUID recipeId;
    private final String name;
    private final Float starRating;
    private final Long starCount;
    private final String recipeImageUrl;
    private final List<String> recipeCategory;
    private final List<String> recipeMaterial;
    private final String recipeTip;
    private final List<RecipeSequence> recipeSequence;
}
