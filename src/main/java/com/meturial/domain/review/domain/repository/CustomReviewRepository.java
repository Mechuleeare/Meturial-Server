package com.meturial.domain.review.domain.repository;

import com.meturial.domain.review.domain.Review;
import com.meturial.domain.review.domain.repository.vo.QueryReviewDetailVo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomReviewRepository {

    List<Review> queryReviewListByRecipeId(UUID recipeId);

    List<Review> queryMyReviewList(UUID userId);
  
    Optional<QueryReviewDetailVo> queryReviewDetail(UUID reviewId);

    List<Float> queryStarRatingListByRecipeId(UUID recipeId);

    Float querySumStarRatingByRecipeId(UUID recipeId);
}
