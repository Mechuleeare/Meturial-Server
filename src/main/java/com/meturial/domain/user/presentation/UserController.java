package com.meturial.domain.user.presentation;

import com.meturial.domain.auth.presentation.dto.request.EmailVerifiedRequest;
import com.meturial.domain.auth.presentation.dto.request.SendEmailRequest;
import com.meturial.domain.user.presentation.dto.request.ModifyMypageRequest;
import com.meturial.domain.user.presentation.dto.request.UserSignUpRequest;
import com.meturial.domain.user.presentation.dto.response.QueryMyInfoResponse;
import com.meturial.domain.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping("/email")
    public void sendEmail(@RequestBody SendEmailRequest request) {
        userService.sendEmail(request);
    }

    @PostMapping("/verify")
    public void verifyCode(@RequestBody @Valid EmailVerifiedRequest request) {
        userService.verifyCode(request);
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

    @PatchMapping("/my-page")
    public void modifyMypage(@RequestBody @Valid ModifyMypageRequest request) {
        userService.modifyMypage(request);
    }
}
