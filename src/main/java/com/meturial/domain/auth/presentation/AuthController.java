package com.meturial.domain.auth.presentation;

import com.meturial.domain.auth.presentation.dto.request.UserSignInRequest;
import com.meturial.domain.auth.presentation.dto.response.TokenResponse;
import com.meturial.domain.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/auth")
@RestController
public class AuthController {

    private final AuthService authService;

    @PostMapping("/token")
    public TokenResponse signIn(@RequestBody UserSignInRequest request) {
        return authService.signIn(request);
    }
}
