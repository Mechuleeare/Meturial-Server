package com.meturial.domain.user.presentation;

import com.meturial.domain.auth.presentation.dto.request.EmailVerifiedRequest;
import com.meturial.domain.user.presentation.dto.request.UserSignUpRequest;
import com.meturial.domain.user.presentation.dto.response.QueryMyInfoResponse;
import com.meturial.domain.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping("/email")
    public void verifyAccount(@RequestBody @Valid EmailVerifiedRequest request) {
        userService.verifyAccount(request);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/signup")
    public void signUp(@RequestBody @Valid UserSignUpRequest request) {
        userService.signUp(request);
    }

    @GetMapping("/my-page")
    public QueryMyInfoResponse getMyInfo() {
        return userService.getMyInfo();
    }
}
