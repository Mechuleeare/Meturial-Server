package com.meturial.domain.review.exception;

import com.meturial.global.error.CustomException;
import com.meturial.global.error.ErrorCode;

public class ReviewExistException extends CustomException {

    public static final CustomException EXCEPTION =
            new ReviewExistException();

    private ReviewExistException() {
        super(ErrorCode.REVIEW_EXIST);
    }
}
