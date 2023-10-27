package com.meturial.domain.recipe.domain.repository.vo;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

import java.util.UUID;

@Getter
public class QueryRecipeRankingVo {
    private final UUID recipeId;
    private final String name;
    private final Float starRating;
    private final Long starCount;
    private final String recipeImageUrl;
    private final String recipeCategory;
    private final String recipeMaterial;

    @QueryProjection
    public QueryRecipeRankingVo(UUID recipeId, String name, Float starRating, Long starCount,
                                String recipeImageUrl, String recipeCategory, String recipeMaterial) {
        this.recipeId = recipeId;
        this.name = name;
        this.starRating = starRating;
        this.starCount = starCount;
        this.recipeImageUrl = recipeImageUrl;
        this.recipeCategory = recipeCategory;
        this.recipeMaterial = recipeMaterial;
    }
}
