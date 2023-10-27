package com.meturial.domain.recipe.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
@Builder
public class RecipeRankingElement {
    private final UUID recipeId;
    private final String name;
    private final Float starRating;
    private final Integer starCount;
    private final String recipeImageUrl;
    private final List<String> recipeCategory;
    private final List<String> recipeMaterial;
}
