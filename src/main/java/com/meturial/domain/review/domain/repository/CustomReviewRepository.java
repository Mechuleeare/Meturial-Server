package com.meturial.domain.review.domain.repository;

import com.meturial.domain.review.domain.Review;

import java.util.List;
import java.util.UUID;

public interface CustomReviewRepository {

    List<Review> queryReviewListByRecipeId(UUID recipeId);
}
