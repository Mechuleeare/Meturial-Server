package com.meturial.domain.menu.exception;

import com.meturial.global.error.CustomException;
import com.meturial.global.error.ErrorCode;

public class MenuNotFoundException extends CustomException {

    public static final CustomException EXCEPTION =
            new MenuNotFoundException();

    private MenuNotFoundException() {
        super(ErrorCode.MENU_NOT_FOUND);
    }
}
