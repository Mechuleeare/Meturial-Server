package com.meturial.domain.recipe.domain.repository.vo;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

import java.util.UUID;

@Getter
public class QueryRecipeDetailVo {
    private final UUID recipeId;
    private final String name;
    private final String recipeImageUrl;
    private final String recipeCategory;
    private final String recipeMaterial;

    @QueryProjection
    public QueryRecipeDetailVo(UUID recipeId, String name, String recipeImageUrl, String recipeCategory, String recipeMaterial) {
        this.recipeId = recipeId;
        this.name = name;
        this.recipeImageUrl = recipeImageUrl;
        this.recipeCategory = recipeCategory;
        this.recipeMaterial = recipeMaterial;
    }
}
