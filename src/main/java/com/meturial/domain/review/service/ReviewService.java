package com.meturial.domain.review.service;

import com.meturial.domain.recipe.domain.Recipe;
import com.meturial.domain.recipe.domain.repository.RecipeRepository;
import com.meturial.domain.recipe.exception.RecipeNotFoundException;
import com.meturial.domain.review.domain.Review;
import com.meturial.domain.review.domain.repository.ReviewRepository;
import com.meturial.domain.review.domain.repository.vo.QueryReviewDetailVo;
import com.meturial.domain.review.exception.ReviewExistException;
import com.meturial.domain.review.exception.ReviewNotFoundException;
import com.meturial.domain.review.presentation.dto.request.CreateReviewRequest;
import com.meturial.domain.review.presentation.dto.request.UpdateReviewRequest;
import com.meturial.domain.review.presentation.dto.response.MyReviewElement;
import com.meturial.domain.review.presentation.dto.response.QueryMyReviewListResponse;
import com.meturial.domain.review.presentation.dto.response.QueryReviewDetailResponse;
import com.meturial.domain.review.presentation.dto.response.QueryReviewListResponse;
import com.meturial.domain.review.presentation.dto.response.ReviewElement;
import com.meturial.domain.user.domain.User;
import com.meturial.global.security.SecurityFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
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

    @Transactional(readOnly = true)
    public QueryReviewListResponse queryReviewListByRecipeId(UUID recipeId) {
        List<Review> reviewList = reviewRepository.queryReviewListByRecipeId(recipeId);
        List<ReviewElement> reviewElements = reviewList
                .stream()
                .map(this::buildReviewElement)
                .toList();

        return new QueryReviewListResponse(
                reviewList.size(),
                reviewList.stream().map(Review::getReviewRecipeName).findFirst().toString(),
                reviewElements
        );
    }

    private ReviewElement buildReviewElement(Review review) {
        return ReviewElement.builder()
                .reviewId(review.getId())
                .writerName(review.getReviewWriterName())
                .starRating(review.getStarRating())
                .reviewImageUrl(review.getReviewImageUrl())
                .content(review.getContent())
                .createdAt(review.getCreatedAt())
                .build();
    }

    @Transactional(readOnly = true)
    public QueryMyReviewListResponse queryMyReviewList() {
        List<Review> myReviewList = reviewRepository.queryMyReviewList(securityFacade.getCurrentUserId());
        List<MyReviewElement> myReviewElements = myReviewList
                .stream()
                .map(this::buildMyReviewElement)
                .toList();

        return new QueryMyReviewListResponse(
                myReviewList.size(),
                myReviewElements
        );
    }

    private MyReviewElement buildMyReviewElement(Review review) {
        return MyReviewElement.builder()
                .reviewId(review.getId())
                .recipeName(review.getReviewRecipeName())
                .starRating(review.getStarRating())
                .content(review.getContent())
                .reviewImageUrl(review.getReviewImageUrl())
                .createdAt(review.getCreatedAt())
                .build();
    }

    @Transactional(readOnly = true)
    public QueryReviewDetailResponse queryReviewDetail(UUID reviewId) {
        QueryReviewDetailVo reviewDetailVo = reviewRepository.queryReviewDetail(reviewId)
                .orElseThrow(() -> ReviewNotFoundException.EXCEPTION);

        return QueryReviewDetailResponse.builder()
                .recipeId(reviewDetailVo.getRecipeId())
                .recipeName(reviewDetailVo.getRecipeName())
                .writerName(reviewDetailVo.getWriterName())
                .starRating(reviewDetailVo.getStarRating())
                .content(reviewDetailVo.getContent())
                .reviewImageUrl(reviewDetailVo.getReviewImageUrl())
                .createdAt(reviewDetailVo.getCreatedAt())
                .build();
    }

    @Transactional
    public void updateReview(UUID reviewId, UpdateReviewRequest request) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> ReviewNotFoundException.EXCEPTION);

        review.checkReviewIsMine(securityFacade.getCurrentUserId());
        review.updateReview(
                request.getStarRating(),
                request.getContent(),
                request.getReviewImageUrl()
        );
    }
}
