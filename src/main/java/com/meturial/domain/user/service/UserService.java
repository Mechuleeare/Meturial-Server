package com.meturial.domain.user.service;

import com.meturial.domain.auth.domain.Certification;
import com.meturial.domain.auth.domain.repository.CertificationRepository;
import com.meturial.domain.auth.domain.type.Certified;
import com.meturial.domain.auth.exception.CodeAlreadyExpiredException;
import com.meturial.domain.auth.exception.CodeNotCorrectException;
import com.meturial.domain.auth.exception.EmailNotCertifiedException;
import com.meturial.domain.auth.presentation.dto.request.EmailVerifiedRequest;
import com.meturial.domain.auth.service.MailService;
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

    private final MailService mailService;
    private final CertificationRepository certificationRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void signUp(UserSignUpRequest request) {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw UserExistException.EXCEPTION;
        }

        mailService.sendEmail(request.getEmail());

        Certification certification = certificationRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> CodeAlreadyExpiredException.EXCEPTION);

        if (certification.getCertified() == (Certified.CERTIFIED)) {
            userRepository.save(User.builder()
                    .name(request.getName())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .email(request.getEmail())
                    .deviceToken(request.getDeviceToken())
                    .build());

        } else throw EmailNotCertifiedException.EXCEPTION;
    }

    @Transactional
    public void verifyAccount(EmailVerifiedRequest request) {
        certificationRepository.findByEmail(request.getEmail())
                .filter(s -> request.getCode().equals(s.getCode()))
                .map(certification -> certificationRepository.save(certification.updateCertified(Certified.CERTIFIED)))
                .orElseThrow(() -> CodeNotCorrectException.EXCEPTION);
    }
}
