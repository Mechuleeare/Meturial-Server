package com.meturial.domain.review.presentation;

import com.meturial.domain.review.presentation.dto.request.CreateReviewRequest;
import com.meturial.domain.review.service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
}
