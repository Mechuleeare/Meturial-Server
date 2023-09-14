package com.meturial.domain.auth.service;

import com.meturial.domain.auth.domain.Certification;
import com.meturial.domain.auth.domain.repository.CertificationRepository;
import com.meturial.domain.auth.domain.type.Certified;
import com.meturial.domain.auth.exception.SendMessageFailedException;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MailService {

    private final JavaMailSender javaMailSender;
    private final CertificationRepository certificationRepository;

    @Value("${code.exp}")
    private Integer CODE_EXP;

    @Value("${spring.mail.username}")
    private String senderEmail;

    @Transactional
    public String createContent(String email) {
        MimeMessage message = javaMailSender.createMimeMessage();

        try {
            String code = createCode();
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

    private String createCode() {
        return RandomStringUtils.randomNumeric(6);
    }
}
