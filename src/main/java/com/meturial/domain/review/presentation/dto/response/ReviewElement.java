package com.meturial.domain.review.presentation.dto.response;

import com.meturial.domain.review.domain.Review;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Builder
public class ReviewElement {
    private final UUID reviewId;
    private final String writerName;
    private final Float starRating;
    private final String reviewImageUrl;
    private final String content;
    private final LocalDateTime createdAt;

    public static ReviewElement of(Review review) {
        return ReviewElement.builder()
                .reviewId(review.getId())
                .writerName(review.getReviewWriterName())
                .starRating(review.getStarRating())
                .reviewImageUrl(review.getReviewImageUrl())
                .content(review.getContent())
                .createdAt(review.getCreatedAt())
                .build();
    }
}
