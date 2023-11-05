package com.meturial.domain.review.exception;

import com.meturial.global.error.CustomException;
import com.meturial.global.error.ErrorCode;

public class ReviewIsNotMineException extends CustomException {

    public static final CustomException EXCEPTION =
            new ReviewIsNotMineException();

    private ReviewIsNotMineException() {
        super(ErrorCode.REVIEW_IS_NOT_MINE);
    }
}
