package com.meturial.domain.review.domain.repository;

import com.meturial.domain.recipe.domain.Recipe;
import com.meturial.domain.review.domain.Review;
import com.meturial.domain.user.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ReviewRepository extends CrudRepository<Review, UUID>, CustomReviewRepository {

    boolean existsByUserAndRecipe(User user, Recipe recipe);
}
