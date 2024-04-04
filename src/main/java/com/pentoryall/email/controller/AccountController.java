package com.pentoryall.email.controller;

import com.pentoryall.email.service.FindPwMail;
import com.pentoryall.email.service.MailService;
import com.pentoryall.email.service.RegisterMail;
import com.pentoryall.user.dto.UserDTO;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class AccountController {
    // 회원가입 메일 서비스
    private final RegisterMail registerMail;

    private final MailService mailService;

    // 임시 패스워드 발송 서비스
    private final FindPwMail findPwMail;

    private final PasswordEncoder passwdEncoder;

    // 이메일 인증
    @PostMapping("/mailConfirm")
    @ResponseBody
    public String mailConfirm(@RequestParam("email") String email) throws Exception {
        System.out.println("email = " + email);

        String code = registerMail.sendSimpleMessage(email);
        System.out.println("인증코드 : " + code);
        return code;
    }

    @GetMapping("/{email_addr}/authcode")
    public ResponseEntity<String> sendEmailPath(@PathVariable String email_addr) throws MessagingException {
        mailService.sendEmailMessage(email_addr);
        return ResponseEntity.ok("이메일을 확인하세요");
    }

    @PostMapping("/{email_addr}/authcode")
    public ResponseEntity<String> sendEmailAndCode(@PathVariable String email_addr, @RequestBody UserDTO user) throws NoSuchAlgorithmException {
        if (mailService.verifyEmailCode(email_addr, String.valueOf(user.getCode()))) {
            return ResponseEntity.ok(mailService.makeMemberId(email_addr));
        }
        return ResponseEntity.notFound().build();
    }
//
//    // 일반 회원 비밀번호 찾기 및 임시 패스워드로 변경
//    @PostMapping("/findMemberPwd")
//    @ResponseBody
//    String findMemberPwd(@RequestParam("mid") String mid, @RequestParam("mname") String mname,
//                         @RequestParam("mphone") String mphone) throws Exception {
//        // System.out.println(mid + " : " + mname + " : " + mphone);
//        UsereDTO mdto = ms.findMemberPwd(mid, mname, mphone);
//
//        if (mdto != null) {
//            // 임시 패스워드 메일 발송 및 변수 저장
//            String tempPw = passwdEncoder.encode(findPwMail.sendSimpleMessage(mdto.getMemail()));
//            // System.out.println("tempPw : " + tempPw);
//            // 임시 패스워드 db 에 저장
//            ms.changeTempPw(tempPw, mdto.getMno());
//
//            return "변경완료";
//        }
//        return null;
//    }
}
