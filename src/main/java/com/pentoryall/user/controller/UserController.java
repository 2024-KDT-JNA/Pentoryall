package com.pentoryall.user.controller;

import com.pentoryall.common.exception.user.MemberRegistException;
import com.pentoryall.user.dto.UserDTO;
import com.pentoryall.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    private final MessageSourceAccessor messageSourceAccessor;

    /* 로그인 페이지 이동 */
    @GetMapping("/login")
    public void loginPage() {
    }

//    @PostMapping("/login")
//    public void login(String userId) {
//        log.info("userId = {}}", userId);
//
//    }

    /* 로그인 실패 시 */
    @PostMapping("/loginfail")
    public String loginFailed(RedirectAttributes rttr) {
        rttr.addFlashAttribute("message", messageSourceAccessor.getMessage("error.login"));
        return "redirect:/user/login";
    }

    /* 회원 가입 페이지 이동 */
    @GetMapping("/regist")
    public void registPage() {
    }

    /* 아이디 중복 체크 : 비동기 통신
     * ResponseEntity : 사용자의 HttpRequest에 대한 응답 데이터를 포함하는 클래스
     * (HttpStatus, HttpHeaders, HttpBody 를 포함한다)
     * 규약에 맞는 HttpResponse를 반환하는데 사용 목적이 있다. */
    @PostMapping("/idDupCheck")
    public ResponseEntity<String> checkDuplication(@RequestBody UserDTO user) {

        log.info("Request Check ID : {}", user.getUserId());

//        boolean isDuplicate = userService.selectUserById(user.getUserId());
//        String result = isDuplicate ? "중복 된 아이디가 존재합니다." : "사용 가능한 아이디입니다.";

        String result = "사용 가능한 아이디입니다.";

        if (userService.selectUserById(user.getUserId())) {
            result = "중복 된 아이디가 존재합니다.";
        }

        return ResponseEntity.ok(result);

    }

    /* 회원 가입 */
    @PostMapping("/regist")
    public String registUser(UserDTO user, RedirectAttributes rttr) throws MemberRegistException {

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        log.info("Request regist user : {}", user);

        userService.registUser(user);

        rttr.addFlashAttribute("message", messageSourceAccessor.getMessage("user.regist"));

        return "redirect:/";
    }
}
