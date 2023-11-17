package com.meturial.domain.recipe.domain.repository.vo;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

import java.util.UUID;

@Getter
public class QueryChoiceRecipeListVo {
    private final UUID choiceId;
    private final UUID recipeId;
    private final String name;
    private final Float starRating;
    private final Integer starCount;
    private final String recipeImageUrl;
    private final String recipeCategory;

    @QueryProjection
    public QueryChoiceRecipeListVo(UUID choiceId, UUID recipeId, String name, Float starRating, Integer starCount,
                                   String recipeImageUrl, String recipeCategory) {
        this.choiceId = choiceId;
        this.recipeId = recipeId;
        this.name = name;
        this.starRating = starRating;
        this.starCount = starCount;
        this.recipeImageUrl = recipeImageUrl;
        this.recipeCategory = recipeCategory;
    }
}
