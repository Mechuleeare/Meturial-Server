package com.meturial.global.exception;

import com.meturial.global.error.CustomException;
import com.meturial.global.error.ErrorCode;

public class SaveImageFailedException extends CustomException {

    public static final CustomException EXCEPTION =
            new SaveImageFailedException();

    private SaveImageFailedException() {
        super(ErrorCode.SAVE_IMAGE_FAILED);
    }
}
