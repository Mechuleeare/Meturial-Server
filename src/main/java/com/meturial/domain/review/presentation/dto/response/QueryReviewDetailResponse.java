package com.meturial.domain.review.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Builder
public class QueryReviewDetailResponse {
    private final UUID recipeId;
    private final String recipeName;
    private final String writerName;
    private final Float starRating;
    private final String content;
    private final String reviewImageUrl;
    private final LocalDateTime createdAt;
}
