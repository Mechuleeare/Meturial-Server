package com.meturial.global.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    EXPIRED_JWT(401, "Expired Jwt"),
    INVALID_JWT(401, "Invalid Jwt"),

    CODE_ALREADY_EXPIRED(401, "Code Already Expired"),
    EMAIL_NOT_CERTIFIED(401, "Email Not Certified"),
    CODE_NOT_CORRECT(401, "Code Not Correct"),
    UN_AUTHORIZED(401, "Un Authorized"),

    USER_NOT_FOUND(404, "User Not Found"),
    REFRESH_TOKEN_NOT_FOUND(404, "Refresh Token Not Found"),

    USER_EXIST(409, "User Exist"),
    RECIPE_NOT_FOUND(409, "Recipe Not Found"),
    CHOICE_RECIPE_EXIST(409, "Choice Recipe Exist"),

    SEND_MESSAGE_FAILED(500, "Send Message Failed"),
    INTERNAL_SERVER_ERROR(500, "Internal Server Error");

    private final int status;
    private final String message;
}
