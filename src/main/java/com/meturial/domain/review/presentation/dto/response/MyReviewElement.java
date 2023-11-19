package com.meturial.domain.review.presentation.dto.response;

import com.meturial.domain.review.domain.Review;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Builder
public class MyReviewElement {
    private final UUID reviewId;
    private final String recipeName;
    private final Float starRating;
    private final String content;
    private final String reviewImageUrl;
    private final LocalDateTime createdAt;

    public static MyReviewElement of(Review review) {
        return MyReviewElement.builder()
                .reviewId(review.getId())
                .recipeName(review.getReviewRecipeName())
                .starRating(review.getStarRating())
                .content(review.getContent())
                .reviewImageUrl(review.getReviewImageUrl())
                .createdAt(review.getCreatedAt())
                .build();
    }
}
