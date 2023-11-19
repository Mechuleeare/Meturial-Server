package com.meturial.domain.review.service;

import com.meturial.domain.recipe.domain.Recipe;
import com.meturial.domain.recipe.facade.RecipeFacade;
import com.meturial.domain.review.domain.Review;
import com.meturial.domain.review.domain.repository.ReviewRepository;
import com.meturial.domain.review.domain.repository.vo.QueryReviewDetailVo;
import com.meturial.domain.review.exception.ReviewExistException;
import com.meturial.domain.review.exception.ReviewNotFoundException;
import com.meturial.domain.review.facade.ReviewFacade;
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

    private final ReviewRepository reviewRepository;
    private final RecipeFacade recipeFacade;
    private final ReviewFacade reviewFacade;
    private final SecurityFacade securityFacade;

    public void createReview(UUID recipeId, CreateReviewRequest request) {
        Recipe recipe = recipeFacade.findById(recipeId);
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
        Review review = reviewFacade.findById(reviewId);
        review.checkReviewIsMine(securityFacade.getCurrentUserId());
        reviewRepository.delete(review);
    }

    @Transactional(readOnly = true)
    public QueryReviewListResponse queryReviewListByRecipeId(UUID recipeId) {
        List<Review> reviewList = reviewRepository.queryReviewListByRecipeId(recipeId);
        List<ReviewElement> reviewElements = reviewList
                .stream()
                .map(ReviewElement::of)
                .toList();

        return new QueryReviewListResponse(
                reviewList.size(),
                reviewList.stream().map(Review::getReviewRecipeName).findFirst().toString(),
                reviewElements
        );
    }

    @Transactional(readOnly = true)
    public QueryMyReviewListResponse queryMyReviewList() {
        List<MyReviewElement> myReviewList = reviewRepository.queryMyReviewList(securityFacade.getCurrentUserId())
                .stream()
                .map(MyReviewElement::of)
                .toList();

        return new QueryMyReviewListResponse(myReviewList.size(), myReviewList);
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
        Review review = reviewFacade.findById(reviewId);
        review.checkReviewIsMine(securityFacade.getCurrentUserId());
        review.updateReview(
                request.getStarRating(),
                request.getContent(),
                request.getReviewImageUrl()
        );
    }
}
