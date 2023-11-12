package com.meturial.domain.menu.exception;

import com.meturial.global.error.CustomException;
import com.meturial.global.error.ErrorCode;

public class MenuIsNotMineException extends CustomException {

    public static final CustomException EXCEPTION =
            new MenuIsNotMineException();

    private MenuIsNotMineException() {
        super(ErrorCode.MENU_IS_NOT_MINE);
    }
}
