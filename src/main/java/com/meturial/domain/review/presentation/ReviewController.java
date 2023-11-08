package com.meturial.domain.review.presentation;

import com.meturial.domain.review.presentation.dto.request.CreateReviewRequest;
import com.meturial.domain.review.presentation.dto.request.UpdateReviewRequest;
import com.meturial.domain.review.presentation.dto.response.QueryMyReviewListResponse;
import com.meturial.domain.review.presentation.dto.response.QueryReviewDetailResponse;
import com.meturial.domain.review.presentation.dto.response.QueryReviewListResponse;
import com.meturial.domain.review.service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping("/review")
@RestController
public class ReviewController {

    private final ReviewService reviewService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{recipe-id}")
    public void createReview(@PathVariable("recipe-id") UUID recipeId,
                             @RequestBody @Valid CreateReviewRequest request) {
        reviewService.createReview(recipeId, request);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{review-id}")
    public void deleteReview(@PathVariable("review-id") UUID reviewId) {
        reviewService.deleteReview(reviewId);
    }

    @GetMapping("/list/{recipe-id}")
    public QueryReviewListResponse queryReviewListByRecipeId(@PathVariable("recipe-id") UUID recipeId) {
        return reviewService.queryReviewListByRecipeId(recipeId);
    }

    @GetMapping("/my")
    public QueryMyReviewListResponse queryMyReviewList() {
        return reviewService.queryMyReviewList();
    }

    @GetMapping("/{review-id}")
    public QueryReviewDetailResponse queryReviewDetail(@PathVariable("review-id") UUID reviewId) {
        return reviewService.queryReviewDetail(reviewId);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/{review-id}")
    public void updateReview(@PathVariable("review-id") UUID reviewId,
                             @RequestBody @Valid UpdateReviewRequest request) {
        reviewService.updateReview(reviewId, request);
    }
}
