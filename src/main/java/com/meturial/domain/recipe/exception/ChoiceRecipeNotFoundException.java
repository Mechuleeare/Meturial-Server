package com.meturial.domain.recipe.exception;

import com.meturial.global.error.CustomException;
import com.meturial.global.error.ErrorCode;

public class ChoiceRecipeNotFoundException extends CustomException {

    public static final CustomException EXCEPTION =
            new ChoiceRecipeNotFoundException();

    private ChoiceRecipeNotFoundException() {
        super(ErrorCode.CHOICE_RECIPE_NOT_FOUND);
    }
}
