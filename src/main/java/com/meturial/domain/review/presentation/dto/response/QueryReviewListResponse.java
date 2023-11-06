package com.meturial.domain.review.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class QueryReviewListResponse {
    private final Integer recipeReviewCount;
    private final List<ReviewElement> recipeReviewList;
}
