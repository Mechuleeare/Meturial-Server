package com.meturial.domain.review.service;

import com.meturial.domain.recipe.domain.Recipe;
import com.meturial.domain.recipe.domain.repository.RecipeRepository;
import com.meturial.domain.recipe.exception.RecipeNotFoundException;
import com.meturial.domain.review.domain.Review;
import com.meturial.domain.review.domain.repository.ReviewRepository;
import com.meturial.domain.review.exception.ReviewExistException;
import com.meturial.domain.review.exception.ReviewNotFoundException;
import com.meturial.domain.review.presentation.dto.request.CreateReviewRequest;
import com.meturial.domain.user.domain.User;
import com.meturial.global.security.SecurityFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ReviewService {

    private final RecipeRepository recipeRepository;
    private final ReviewRepository reviewRepository;
    private final SecurityFacade securityFacade;

    public void createReview(UUID recipeId, CreateReviewRequest request) {
        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> RecipeNotFoundException.EXCEPTION);

        User user = securityFacade.getCurrentUser();

        if (reviewRepository.existsByUserAndRecipe(user, recipe)) {
            throw ReviewExistException.EXCEPTION;
        }

        reviewRepository.save(Review.builder()
                .recipe(recipe)
                .user(user)
                .starRating(request.getStarRating())
                .content(request.getContent())
                .reviewImageUrl(request.getReviewImageUrl())
                .createdAt(LocalDateTime.now())
                .build());
    }

    @Transactional
    public void deleteReview(UUID reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> ReviewNotFoundException.EXCEPTION);

        review.checkReviewIsMine(securityFacade.getCurrentUserId());

        reviewRepository.delete(review);
    }
}
