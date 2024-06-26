package com.pentoryall.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class EmailConfig {
    @Bean
    public JavaMailSender javaMailService() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();

        javaMailSender.setHost("smtp.gmail.com"); // 메인 도메인 서버 주소 => 정확히는 smtp 서버 주소
        javaMailSender.setUsername("pentoryall@gmail.com"); // 구글 아이디
        javaMailSender.setPassword("arwurhhcgtqphdce"); // 구글 비밀번호

        javaMailSender.setPort(587); // 메일 인증서버 포트

        javaMailSender.setJavaMailProperties(getMailProperties()); // 메일 인증서버 정보 가져오기

        return javaMailSender;
    }

    private Properties getMailProperties() {
        Properties properties = new Properties();
        properties.setProperty("mail.transport.protocol", "smtp"); // 프로토콜 설정
        properties.setProperty("mail.smtp.auth", "true"); // smtp 인증
        properties.setProperty("mail.smtp.starttls.enable", "true"); // smtp strattles 사용
        properties.setProperty("mail.debug", "true"); // 디버그 사용
//        properties.setProperty("mail.smtp.ssl.trust", "smtp.gmail.com"); // ssl 인증 서버는 smtp.naver.com
//        properties.setProperty("mail.smtp.ssl.enable", "true"); // ssl 사용
        return properties;
    }
//
//    @Value("${spring.mail.host}")
//    private String host;
//
//    @Value("${spring.mail.port}")
//    private int port;
//
//    @Value("${spring.mail.username}")
//    private String username;
//
//    @Value("${spring.mail.password}")
//    private String password;
//
//    @Value("${spring.mail.properties.mail.smtp.auth}")
//    private boolean auth;
//
//    @Value("${spring.mail.properties.mail.smtp.starttls.enable}")
//    private boolean starttlsEnable;
//
//    @Value("${spring.mail.properties.mail.smtp.starttls.required}")
//    private boolean starttlsRequired;
//
//    @Value("${spring.mail.properties.mail.smtp.connectiontimeout}")
//    private int connectionTimeout;
//
//    @Value("${spring.mail.properties.mail.smtp.timeout}")
//    private int timeout;
//
//    @Value("${spring.mail.properties.mail.smtp.writetimeout}")
//    private int writeTimeout;
//
//    @Bean
//    public JavaMailSender javaMailSender() {
//        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
//        mailSender.setHost(host);
//        mailSender.setPort(port);
//        mailSender.setUsername(username);
//        mailSender.setPassword(password);
//        mailSender.setDefaultEncoding("UTF-8");
//        mailSender.setJavaMailProperties(getMailProperties());
//
//        return mailSender;
//    }
//
//    private Properties getMailProperties() {
//        Properties properties = new Properties();
//        properties.put("mail.smtp.auth", auth);
//        properties.put("mail.smtp.starttls.enable", starttlsEnable);
//        properties.put("mail.smtp.starttls.required", starttlsRequired);
//        properties.put("mail.smtp.connectiontimeout", connectionTimeout);
//        properties.put("mail.smtp.timeout", timeout);
//        properties.put("mail.smtp.writetimeout", writeTimeout);
//
//        return properties;
//    }
}