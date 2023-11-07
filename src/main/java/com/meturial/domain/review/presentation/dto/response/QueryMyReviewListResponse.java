package com.meturial.domain.review.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class QueryMyReviewListResponse {
    private final Integer myReviewCount;
    private final List<MyReviewElement> myReviewList;
}
