package com.pentoryall.email.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
/* 이메 발송을 담당하는 클래스 */
public class MailService {
    private final JavaMailSender emailSender;

//    public void sendEmail(String toEmail,
//                          String title,
//                          String text) {
//        SimpleMailMessage emailForm = createEmailForm(toEmail, title, text);
//        try {
//            emailSender.send(emailForm);
//        } catch (RuntimeException e) {
//            log.debug("MailService.sendEmail exception occur toEmail: {}, " +
//                    "title: {}, text: {}", toEmail, title, text);
//            throw new BusinessLogicException(ExceptionCode.UNABLE_TO_SEND_EMAIL);
//        }
//    }

    // 발신할 이메일 데이터 세팅
    private SimpleMailMessage createEmailForm(String toEmail,
                                              String title,
                                              String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject(title);
        message.setText(text);

        return message;
    }
}
