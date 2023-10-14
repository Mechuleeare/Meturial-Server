package com.meturial.domain.recipe.exception;

import com.meturial.global.error.CustomException;
import com.meturial.global.error.ErrorCode;

public class RecipeNotFoundException extends CustomException {

    public static final CustomException EXCEPTION =
            new RecipeNotFoundException();

    private RecipeNotFoundException() {
        super(ErrorCode.RECIPE_NOT_FOUND);
    }
}
