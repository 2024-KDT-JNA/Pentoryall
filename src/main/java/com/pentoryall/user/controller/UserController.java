package com.pentoryall.user.controller;

import com.pentoryall.common.exception.user.MemberModifyException;
import com.pentoryall.common.exception.user.MemberRegistException;
import com.pentoryall.common.exception.user.MemberRemoveException;
import com.pentoryall.user.dto.UserDTO;
import com.pentoryall.user.service.AuthService;
import com.pentoryall.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/views/user")
public class UserController {

    private final UserService userService;

    private final AuthService authenticationService;

    private final PasswordEncoder passwordEncoder;

    private final MessageSourceAccessor messageSourceAccessor;

    /* 로그인 페이지 이동 */
    @GetMapping("/login")
    public String loginPage(@RequestParam(required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("errorMessage", "존재하지 않는 회원입니다.");
        }
        return "views/user/login";
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
        System.out.println(messageSourceAccessor.getMessage("error.login"));
        return "redirect:/views/user/login";
    }

    /* 아이디 중복 체크 : 비동기 통신
     * ResponseEntity : 사용자의 HttpRequest에 대한 응답 데이터를 포함하는 클래스
     * (HttpStatus, HttpHeaders, HttpBody 를 포함한다)
     * 규약에 맞는 HttpResponse를 반환하는데 사용 목적이 있다. */
    @PostMapping("/idDupCheck")
    public ResponseEntity<String> checkDuplication(@RequestBody UserDTO user) {

        log.info("Request Check ID : {}", user.getUserId());

        boolean isDuplicate = userService.selectUserById(user.getUserId());
        String result = isDuplicate ? "중복 된 아이디가 존재합니다." : "사용 가능한 아이디입니다.";

//        String result = "사용 가능한 아이디입니다.";
//
//        if (userService.selectUserById(user.getUserId())) {
//            result = "중복 된 아이디가 존재합니다.";
//        }

        return ResponseEntity.ok(result);

    }

    protected Authentication createNewAuthentication(String userId) {

        UserDetails newPrincipal = authenticationService.loadUserByUsername(userId);
        UsernamePasswordAuthenticationToken newAuth
                = new UsernamePasswordAuthenticationToken(newPrincipal, newPrincipal.getPassword(), newPrincipal.getAuthorities());

        return newAuth;
    }

    @GetMapping("/update")
    public String updatePage() {
        return "views/user/myModify";
    }

    /* 회원 가입 페이지 이동 */
    @GetMapping("/regist")
    public String registPage() {
        return "views/user/regist";
    }

    /* 회원 가입 */
    @PostMapping("/regist")
    public String registUser(UserDTO user, RedirectAttributes rttr) throws MemberRegistException {

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        log.info("Request regist user : {}", user);

        // 회원 가입 시 중복 체크를 다시 한번 수행합니다.
        if (userService.selectUserById(user.getUserId())) {
            rttr.addFlashAttribute("errorMessage", "이미 존재하는 아이디입니다. 다른 아이디를 입력해주세요.");
            return "redirect:/views/user/regist";
        }

        userService.registUser(user);

        rttr.addFlashAttribute("message", messageSourceAccessor.getMessage("user.regist"));

        return "redirect:/";
    }

    /* 회원 정보 수정 */
    @PostMapping("/update")
    public String modifyUser(UserDTO modifyUser,
                             @AuthenticationPrincipal UserDTO loginUser, RedirectAttributes rttr) throws MemberModifyException {

        modifyUser.setUserId(loginUser.getUserId());

        log.info("modifyUser request User : {}", modifyUser);

        userService.modifyUser(modifyUser);

        /* 로그인 시 저장 된 Authentication 객체를 변경 된 정보로 교체한다. */
        SecurityContextHolder.getContext().setAuthentication(createNewAuthentication(loginUser.getUserId()));

        rttr.addFlashAttribute("message", messageSourceAccessor.getMessage("user.modify"));

        return "redirect:/";
    }

    @GetMapping("/withdrawal")
    public String getUserDeletePage() {
        return "views/user/withdrawal";
    }

    /* 회원 탈퇴 */
    @GetMapping("/delete")
    public String deleteUser(@AuthenticationPrincipal UserDTO user, RedirectAttributes rttr) throws MemberRemoveException {

        log.info("login user : {}", user);

        /* 회원을 db에서 삭제 */
        userService.removeUser(user);

        /* 로그아웃 처리 (세션만료) */
        return "redirect:views/user/logout";
    }
}
