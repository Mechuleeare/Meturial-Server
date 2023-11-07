package com.meturial.domain.review.presentation.dto.response;

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
}
