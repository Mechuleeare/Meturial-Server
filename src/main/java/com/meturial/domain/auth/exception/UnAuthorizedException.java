package com.meturial.domain.auth.exception;

import com.meturial.global.error.CustomException;
import com.meturial.global.error.ErrorCode;

public class UnAuthorizedException extends CustomException {

    public static final CustomException EXCEPTION =
            new UnAuthorizedException();

    private UnAuthorizedException() {
        super(ErrorCode.UN_AUTHORIZED);
    }
}
