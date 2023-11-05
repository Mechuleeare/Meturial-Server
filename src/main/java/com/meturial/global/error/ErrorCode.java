package com.meturial.global.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    SAVE_IMAGE_FAILED(400, "Save Image Failed"),

    EXPIRED_JWT(401, "Expired Jwt"),
    INVALID_JWT(401, "Invalid Jwt"),

    CODE_ALREADY_EXPIRED(401, "Code Already Expired"),
    EMAIL_NOT_CERTIFIED(401, "Email Not Certified"),
    CODE_NOT_CORRECT(401, "Code Not Correct"),
    UN_AUTHORIZED(401, "Un Authorized"),

    CHOICE_RECIPE_IS_NOT_MINE(403, "Choice Recipe Is Not Mine"),
    REVIEW_IS_NOT_MINE(403, "Review Is Not Mine"),

    USER_NOT_FOUND(404, "User Not Found"),
    RECIPE_NOT_FOUND(404, "Recipe Not Found"),
    RECIPE_SEQUENCE_NOT_FOUND(404, "Recipe Sequence Not Found"),
    CHOICE_RECIPE_NOT_FOUND(404, "Choice Recipe Not Found"),
    REVIEW_NOT_FOUND(404, "Review Not Found"),
    REFRESH_TOKEN_NOT_FOUND(404, "Refresh Token Not Found"),

    USER_EXIST(409, "User Exist"),
    CHOICE_RECIPE_EXIST(409, "Choice Recipe Exist"),

    SEND_MESSAGE_FAILED(500, "Send Message Failed"),
    INTERNAL_SERVER_ERROR(500, "Internal Server Error");

    private final int status;
    private final String message;
}
