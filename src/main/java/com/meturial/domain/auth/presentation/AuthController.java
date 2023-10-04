package com.meturial.domain.auth.presentation;

import com.meturial.domain.auth.presentation.dto.request.ChangePasswordRequest;
import com.meturial.domain.auth.presentation.dto.request.UserSignInRequest;
import com.meturial.domain.auth.presentation.dto.response.TokenResponse;
import com.meturial.domain.auth.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/auth")
@RestController
public class AuthController {

    private final AuthService authService;

    @PostMapping("/token")
    public TokenResponse signIn(@RequestBody UserSignInRequest request) {
        return authService.signIn(request);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/logout")
    public void logOut() {
        authService.logOut();
    }

    @PutMapping("/find")
    public void changePassword(@RequestBody @Valid ChangePasswordRequest request) {
        authService.changePassword(request);
    }
}
