package com.meturial.domain.review.exception;

import com.meturial.global.error.CustomException;
import com.meturial.global.error.ErrorCode;

public class ReviewNotFoundException extends CustomException {

    public static final CustomException EXCEPTION =
            new ReviewNotFoundException();

    private ReviewNotFoundException() {
        super(ErrorCode.REVIEW_NOT_FOUND);
    }
}
