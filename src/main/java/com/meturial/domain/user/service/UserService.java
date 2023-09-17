package com.meturial.domain.user.service;

import com.meturial.domain.auth.domain.Certification;
import com.meturial.domain.auth.domain.repository.CertificationRepository;
import com.meturial.domain.auth.domain.type.Certified;
import com.meturial.domain.auth.exception.CodeAlreadyExpiredException;
import com.meturial.domain.auth.exception.CodeNotCorrectException;
import com.meturial.domain.auth.exception.EmailNotCertifiedException;
import com.meturial.domain.auth.presentation.dto.request.EmailVerifiedRequest;
import com.meturial.domain.auth.service.AuthService;
import com.meturial.domain.user.domain.User;
import com.meturial.domain.user.domain.repository.UserRepository;
import com.meturial.domain.user.exception.UserExistException;
import com.meturial.domain.user.presentation.dto.request.UserSignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final AuthService authService;
    private final CertificationRepository certificationRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void signUp(UserSignUpRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw UserExistException.EXCEPTION;
        }

        authService.sendEmail(request.getEmail());

        Certification certification = certificationRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> CodeAlreadyExpiredException.EXCEPTION);

        if (certification.getCertified() != Certified.CERTIFIED) {
            throw EmailNotCertifiedException.EXCEPTION;
        }

        userRepository.save(User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .name(request.getName())
                .deviceToken(request.getDeviceToken())
                .build());
    }

    @Transactional
    public void verifyAccount(EmailVerifiedRequest request) {
        certificationRepository.findByEmail(request.getEmail())
                .filter(s -> request.getCode().equals(s.getCode()))
                .map(certification -> certificationRepository.save(certification.updateCertified(Certified.CERTIFIED)))
                .orElseThrow(() -> CodeNotCorrectException.EXCEPTION);
    }
}
