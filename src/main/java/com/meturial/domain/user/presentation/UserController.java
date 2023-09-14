package com.meturial.domain.user.presentation;

import com.meturial.domain.auth.presentation.dto.request.EmailVerifiedRequest;
import com.meturial.domain.user.presentation.dto.request.UserSignUpRequest;
import com.meturial.domain.user.service.UserSignUpService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
public class UserController {

    private final UserSignUpService userSignUpService;

    @PostMapping("/email")
    public void verifyAccount(@RequestBody @Valid EmailVerifiedRequest request) {
        userSignUpService.verifyAccount(request);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/signup")
    public void signUp(@RequestBody @Valid UserSignUpRequest request) {
        userSignUpService.signUp(request);
    }
}
