package com.meturial.domain.auth.exception;

import com.meturial.global.error.CustomException;
import com.meturial.global.error.ErrorCode;

public class CodeAlreadyExpiredException extends CustomException {

    public static final CustomException EXCEPTION =
            new CodeAlreadyExpiredException();

    private CodeAlreadyExpiredException() {
        super(ErrorCode.CODE_ALREADY_EXPIRED);
    }
}
