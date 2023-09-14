package com.meturial.domain.user.exception;

import com.meturial.global.error.CustomException;
import com.meturial.global.error.ErrorCode;

public class UserExistException extends CustomException {

    public static final CustomException EXCEPTION =
            new UserExistException();

    private UserExistException() {
        super(ErrorCode.USER_EXIST);
    }
}
