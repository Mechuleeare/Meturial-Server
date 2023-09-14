package com.meturial.domain.auth.exception;

import com.meturial.global.error.CustomException;
import com.meturial.global.error.ErrorCode;

public class SendMessageFailedException extends CustomException {

    public static final CustomException EXCEPTION =
            new SendMessageFailedException();

    private SendMessageFailedException() {
        super(ErrorCode.SEND_MESSAGE_FAILED);
    }
}
