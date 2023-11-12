package com.meturial.domain.menu.exception;

import com.meturial.global.error.CustomException;
import com.meturial.global.error.ErrorCode;

public class MenuExistException extends CustomException {

    public static final CustomException EXCEPTION =
            new MenuExistException();

    private MenuExistException() {
        super(ErrorCode.MENU_EXIST);
    }
}
