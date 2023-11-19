package com.meturial.domain.user.service;

import com.meturial.domain.auth.domain.Certification;
import com.meturial.domain.auth.domain.repository.CertificationRepository;
import com.meturial.domain.auth.domain.type.Certified;
import com.meturial.domain.auth.exception.CodeAlreadyExpiredException;
import com.meturial.domain.auth.exception.CodeNotCorrectException;
import com.meturial.domain.auth.presentation.dto.request.EmailVerifiedRequest;
import com.meturial.domain.auth.presentation.dto.request.SendEmailRequest;
import com.meturial.domain.auth.service.AuthService;
import com.meturial.domain.user.domain.User;
import com.meturial.domain.user.domain.repository.UserRepository;
import com.meturial.domain.user.exception.UserExistException;
import com.meturial.domain.user.presentation.dto.request.ModifyMypageRequest;
import com.meturial.domain.user.presentation.dto.request.UserSignUpRequest;
import com.meturial.domain.user.presentation.dto.response.QueryMyInfoResponse;
import com.meturial.global.security.SecurityFacade;
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
    private final SecurityFacade securityFacade;

    @Transactional
    public void signUp(UserSignUpRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw UserExistException.EXCEPTION;
        }

        Certification certification = certificationRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> CodeAlreadyExpiredException.EXCEPTION);

        certification.checkIsCertified();

        userRepository.save(User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .name(request.getName())
                .profileImageUrl(request.getProfileImageUrl())
                .deviceToken(request.getDeviceToken())
                .allergyInfo(request.getAllergyInfo())
                .build());
    }

    @Transactional
    public void sendEmail(SendEmailRequest request) {
        authService.sendEmail(request.getEmail());
    }

    @Transactional
    public void verifyCode(EmailVerifiedRequest request) {
        certificationRepository.findByEmail(request.getEmail())
                .filter(s -> request.getCode().equals(s.getCode()))
                .map(certification -> certificationRepository.save(certification.updateCertified(Certified.CERTIFIED)))
                .orElseThrow(() -> CodeNotCorrectException.EXCEPTION);
    }

    @Transactional(readOnly = true)
    public QueryMyInfoResponse getMyInfo() {
        User user = securityFacade.getCurrentUser();

        return QueryMyInfoResponse.builder()
                .profileImageUrl(user.getProfileImageUrl())
                .name(user.getName())
                .allergyInfo(user.getAllergyInfo())
                .email(user.getEmail())
                .build();
    }

    @Transactional
    public void modifyMyInfo(ModifyMypageRequest request) {
        User user = securityFacade.getCurrentUser();

        user.modifyMyInfo(
                request.getProfileImageUrl(),
                request.getName(),
                request.getAllergyInfo()
        );
    }
}
