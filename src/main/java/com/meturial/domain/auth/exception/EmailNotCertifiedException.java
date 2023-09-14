package com.meturial.domain.auth.exception;

import com.meturial.global.error.CustomException;
import com.meturial.global.error.ErrorCode;

public class EmailNotCertifiedException extends CustomException {

    public static final CustomException EXCEPTION =
            new EmailNotCertifiedException();

    private EmailNotCertifiedException() {
        super(ErrorCode.EMAIL_NOT_CERTIFIED);
    }
}
