package com.meturial.domain.auth.service;

import com.meturial.domain.auth.domain.Certification;
import com.meturial.domain.auth.domain.RefreshToken;
import com.meturial.domain.auth.domain.repository.CertificationRepository;
import com.meturial.domain.auth.domain.repository.RefreshTokenRepository;
import com.meturial.domain.auth.domain.type.Certified;
import com.meturial.domain.auth.exception.RefreshTokenNotFoundException;
import com.meturial.domain.auth.exception.SendMessageFailedException;
import com.meturial.domain.auth.exception.UnAuthorizedException;
import com.meturial.domain.auth.presentation.dto.request.UserSignInRequest;
import com.meturial.domain.auth.presentation.dto.response.TokenResponse;
import com.meturial.domain.user.domain.User;
import com.meturial.domain.user.domain.repository.UserRepository;
import com.meturial.global.security.SecurityFacade;
import com.meturial.global.security.jwt.JwtTokenProvider;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final JavaMailSender javaMailSender;
    private final CertificationRepository certificationRepository;
    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final SecurityFacade securityFacade;

    @Value("${code.exp}")
    private Integer CODE_EXP;

    @Value("${spring.mail.username}")
    private String senderEmail;

    @Transactional
    public String createContent(String email) {
        MimeMessage message = javaMailSender.createMimeMessage();

        try {
            String code = RandomStringUtils.randomNumeric(6);
            message.setFrom(senderEmail);
            message.setRecipients(Message.RecipientType.TO, email);
            message.setSubject("[메추리알] 인증 메일");
            message.setText(code);

            javaMailSender.send(message);

            return code;
        } catch (MessagingException e) {
            throw SendMessageFailedException.EXCEPTION;
        }
    }

    @Transactional
    public void sendEmail(String email) {
        certificationRepository.findByEmail(email)
                .map(certification -> certificationRepository.save(certification.updateCode(createContent(email))))
                .orElseGet(() -> certificationRepository.save(Certification.builder()
                        .code(createContent(email))
                        .email(email)
                        .certified(Certified.NOT_CERTIFIED)
                        .codeExp(CODE_EXP)
                        .build()));
    }

    public TokenResponse signIn(UserSignInRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> UnAuthorizedException.EXCEPTION);

        boolean isPasswordMatches = passwordEncoder.matches(request.getPassword(), user.getPassword());
        if (!isPasswordMatches) {
            throw UnAuthorizedException.EXCEPTION;
        }

        return jwtTokenProvider.getTokens(user.getEmail());
    }

    @Transactional
    public void logOut() {
        User user = securityFacade.getCurrentUser();

        RefreshToken refreshToken = refreshTokenRepository.findById(user.getEmail())
                .orElseThrow(() -> RefreshTokenNotFoundException.EXCEPTION);

        refreshTokenRepository.delete(refreshToken);
    }
}
