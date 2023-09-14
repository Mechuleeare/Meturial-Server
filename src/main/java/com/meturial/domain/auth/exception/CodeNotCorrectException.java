package com.meturial.domain.auth.exception;

import com.meturial.global.error.CustomException;
import com.meturial.global.error.ErrorCode;

public class CodeNotCorrectException extends CustomException {

    public static final CustomException EXCEPTION =
            new CodeNotCorrectException();

    private CodeNotCorrectException() {
        super(ErrorCode.CODE_NOT_CORRECT);
    }
}
