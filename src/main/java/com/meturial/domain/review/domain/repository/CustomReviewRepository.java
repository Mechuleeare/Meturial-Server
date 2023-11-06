package com.meturial.domain.review.domain.repository;

import com.meturial.domain.review.domain.repository.vo.QueryReviewDetailVo;

import java.util.Optional;
import java.util.UUID;

public interface CustomReviewRepository {
    Optional<QueryReviewDetailVo> queryReviewDetail(UUID reviewId);
}
