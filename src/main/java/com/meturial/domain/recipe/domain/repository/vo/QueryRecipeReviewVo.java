package com.meturial.domain.recipe.domain.repository.vo;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

import java.util.UUID;

@Getter
public class QueryRecipeReviewVo {
    private final UUID recipeId;
    private final Float starRating;
    private final Long starCount;

    @QueryProjection
    public QueryRecipeReviewVo(UUID recipeId, Float starRating, Long starCount) {
        this.recipeId = recipeId;
        this.starRating = starRating;
        this.starCount = starCount;
    }
}
