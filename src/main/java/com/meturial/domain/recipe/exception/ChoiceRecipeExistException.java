package com.meturial.domain.recipe.exception;

import com.meturial.global.error.CustomException;
import com.meturial.global.error.ErrorCode;

public class ChoiceRecipeExistException extends CustomException {

    public static final CustomException EXCEPTION =
            new ChoiceRecipeExistException();

    private ChoiceRecipeExistException() {
        super(ErrorCode.CHOICE_RECIPE_EXIST);
    }
}
