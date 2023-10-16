package com.meturial.domain.recipe.exception;

import com.meturial.global.error.CustomException;
import com.meturial.global.error.ErrorCode;

public class ChoiceRecipeIsNotMineException extends CustomException {

    public static final CustomException EXCEPTION =
            new ChoiceRecipeIsNotMineException();

    private ChoiceRecipeIsNotMineException() {
        super(ErrorCode.CHOICE_RECIPE_IS_NOT_MINE);
    }
}
