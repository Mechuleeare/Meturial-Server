package com.meturial.domain.recipe.exception;

import com.meturial.global.error.CustomException;
import com.meturial.global.error.ErrorCode;

public class RecipeSequenceNotFoundException extends CustomException {

    public static final CustomException EXCEPTION =
            new RecipeSequenceNotFoundException();

    private RecipeSequenceNotFoundException() {
        super(ErrorCode.RECIPE_SEQUENCE_NOT_FOUND);
    }
}
