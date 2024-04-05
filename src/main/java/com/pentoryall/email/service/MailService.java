package com.pentoryall.email.service;

import com.pentoryall.email.EmailProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Random;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
/* 이메 발송을 담당하는 클래스 */
public class MailService {
    private final JavaMailSender mailSender;
    //    private final RedisUtil redisUtil;
    private final EmailProperties emailProperties;


    @Value("${spring.mail.username}")
    private String configEmail;

    private String createdCode() {
        int leftLimit = 48; // number '0'
        int rightLimit = 122; // alphabet 'z'
        int targetStringLength = 6;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    private String setContext(String code) {
        Context context = new Context();
        TemplateEngine templateEngine = new TemplateEngine();
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();

        context.setVariable("code", code);


        templateResolver.setPrefix("templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCacheable(false);

        templateEngine.setTemplateResolver(templateResolver);

        return templateEngine.process("mail", context);
    }


//    // 메일 반환
//
//    private MimeMessage createEmailForm(String email) throws MessagingException {
//
//        String authCode = createdCode();
//
//        MimeMessage message = mailSender.createMimeMessage();
//        message.addRecipients(MimeMessage.RecipientType.TO, email);
//        message.setSubject("안녕하세요 인증번호입니다.");
//        message.setFrom(configEmail);
//        message.setText(setContext(authCode), "utf-8", "html");
//
//        redisUtil.setDataExpire(email, authCode, 60 * 30L);
//
//        return message;
//    }


    // 메일 보내기
    public void sendEmailMessage(String email) {
//        try {
//            String code = createdCode();
//            redisUtil.setDataExpire(code, email, emailProperties.getValidTime());
//            MimeMessage message = createMessage(email, code);
//            mailSender.send(message);
//        } catch (Exception e) {
//            throw new EmailSendException();
//        }
    }

//    public Long getUserIdByCode(String code) {
//        String email = redisUtil.getData(code); // 입력 받은 인증 코드(key)를 이용해 email(value)을 꺼낸다.
//        if (email == null) { // email이 존재하지 않으면, 유효 기간 만료이거나 코드 잘못 입력
//            throw new EmailCodeException();
//        }
//
//        User user = userValidator.checkEmailPresent(email); // 해당 email로 user를 꺼낸다.
//        return user.getId();
//    }

//    // 코드 검증
//    public Boolean verifyEmailCode(String email, String code) {
//        String codeFoundByEmail = redisUtil.getData(email);
//        System.out.println(codeFoundByEmail);
//        if (codeFoundByEmail == null) {
//            return false;
//        }
//        return codeFoundByEmail.equals(code);
//    }

    public String makeMemberId(String email) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(email.getBytes());
        md.update(LocalDateTime.now().toString().getBytes());
        return Arrays.toString(md.digest());
    }
}