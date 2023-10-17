package com.meturial.domain.review.domain.repository;

import com.meturial.domain.review.domain.Review;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ReviewRepository extends CrudRepository<Review, UUID> {
    Long countByRecipeId(UUID recipeId);
}
