package com.meturial.domain.recipe.domain.repository.vo;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

import java.util.UUID;

@Getter
public class QueryChoiceRecipeListVo {
    private UUID choiceId;
    private UUID recipeId;
    private String name;
    private Float starRating;
    private Integer starCount;
    private String recipeImageUrl;
    private String recipeCategory;

    @QueryProjection
    public QueryChoiceRecipeListVo(UUID choiceId, UUID recipeId, String name, Float starRating, Integer starCount,
                                   String recipeImageUrl, String recipeCategory) {
        this.choiceId = choiceId;
        this.recipeId = recipeId;
        this.name = name;
        this.starRating = (starRating != null) ? starRating : Float.valueOf(0);
        this.starCount = starCount;
        this.recipeImageUrl = recipeImageUrl;
        this.recipeCategory = recipeCategory;
    }
}
