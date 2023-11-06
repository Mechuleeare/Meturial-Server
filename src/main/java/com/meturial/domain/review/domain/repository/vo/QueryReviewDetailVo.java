package com.meturial.domain.review.domain.repository.vo;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class QueryReviewDetailVo {
    private final UUID recipeId;
    private final String recipeName;
    private final String writerName;
    private final Float starRating;
    private final String content;
    private final String reviewImageUrl;
    private final LocalDateTime createdAt;

    @QueryProjection
    public QueryReviewDetailVo(UUID recipeId, String recipeName, String writerName, Float starRating,
                               String content, String reviewImageUrl, LocalDateTime createdAt) {
        this.recipeId = recipeId;
        this.recipeName = recipeName;
        this.writerName = writerName;
        this.starRating = starRating;
        this.content = content;
        this.reviewImageUrl = reviewImageUrl;
        this.createdAt = createdAt;
    }
}
