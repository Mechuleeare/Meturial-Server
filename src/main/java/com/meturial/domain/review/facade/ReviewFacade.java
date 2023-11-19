package com.meturial.domain.review.facade;

import com.meturial.domain.review.domain.Review;
import com.meturial.domain.review.domain.repository.ReviewRepository;
import com.meturial.domain.review.exception.ReviewNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@RequiredArgsConstructor
@Component
public class ReviewFacade {

    private final ReviewRepository reviewRepository;

    public Review findById(UUID reviewId) {
        return reviewRepository.findById(reviewId)
                .orElseThrow(() -> ReviewNotFoundException.EXCEPTION);
    }
}
