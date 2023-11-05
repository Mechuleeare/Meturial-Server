package com.meturial.domain.review.service;

import com.meturial.domain.review.domain.Review;
import com.meturial.domain.review.domain.repository.ReviewRepository;
import com.meturial.domain.review.exception.ReviewNotFoundException;
import com.meturial.global.security.SecurityFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final SecurityFacade securityFacade;

    @Transactional
    public void deleteReview(UUID reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> ReviewNotFoundException.EXCEPTION);

        review.checkReviewIsMine(securityFacade.getCurrentUserId());

        reviewRepository.delete(review);
    }
}
