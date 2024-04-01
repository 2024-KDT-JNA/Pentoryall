package com.pentoryall.user.controller;

import com.pentoryall.common.exception.user.MemberModifyException;
import com.pentoryall.common.exception.user.MemberRegistException;
import com.pentoryall.common.exception.user.MemberRemoveException;
import com.pentoryall.user.dto.UserDTO;
import com.pentoryall.user.service.AuthService;
import com.pentoryall.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class UserController {

    @Value("${image.image-dir}")
    private String IMAGE_DIR;

    private final UserService userService;

    private final AuthService authenticationService;

    private final PasswordEncoder passwordEncoder;

    private final MessageSourceAccessor messageSourceAccessor;

    /* 로그인 페이지 이동 */
    @GetMapping("/login")
    public String loginPage(Model model) {
//        if (error != null) {
//            model.addAttribute("errorMessage", "존재하지 않는 회원입니다.");
//        }
        return "views/user/login";
    }


    /* 로그인 실패 시 */
    @PostMapping("/loginfail")
    public String loginFailed(RedirectAttributes rttr) {
        rttr.addFlashAttribute("message", messageSourceAccessor.getMessage("error.login"));
        System.out.println(messageSourceAccessor.getMessage("error.login"));
        return "redirect:/user/login";
    }

    /* 아이디 중복 체크 : 비동기 통신
     * ResponseEntity : 사용자의 HttpRequest에 대한 응답 데이터를 포함하는 클래스
     * (HttpStatus, HttpHeaders, HttpBody 를 포함한다)
     * 규약에 맞는 HttpResponse를 반환하는데 사용 목적이 있다. */
    @PostMapping("/idDupCheck")
    public ResponseEntity<Boolean> checkDuplication(@RequestBody UserDTO user) {

        log.info("Request Check ID : {}", user.getUserId());

        boolean isDuplicate = userService.selectUserById(user.getUserId());
        Boolean result = isDuplicate ? true : false;

//        String result = "사용 가능한 아이디입니다.";
//
//        if (userService.selectUserById(user.getUserId())) {
//            result = "중복 된 아이디가 존재합니다.";
//        }
        log.info(String.valueOf(result));

        return ResponseEntity.ok(result);

    }

    protected Authentication createNewAuthentication(String userId) {

        UserDetails newPrincipal = authenticationService.loadUserByUsername(userId);
        UsernamePasswordAuthenticationToken newAuth
                = new UsernamePasswordAuthenticationToken(newPrincipal, newPrincipal.getPassword(), newPrincipal.getAuthorities());

        return newAuth;
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
            return "redirect:/user/regist";
        }

        userService.registUser(user);

        rttr.addFlashAttribute("message", messageSourceAccessor.getMessage("user.regist"));

        return "redirect:/";
    }

    /* 회원정보 수정 페이지로 */
    @GetMapping("/update")
    public String updatePage() {
        return "views/user/myModify";
    }

    /* 회원 정보 수정 */
    @PostMapping("/update")
    public String modifyUser(UserDTO modifyUser,
                             @AuthenticationPrincipal UserDTO loginUser, RedirectAttributes rttr,
                             Model model,
                             @RequestParam(required = false) MultipartFile profile) throws MemberModifyException {

        modifyUser.setUserId(loginUser.getUserId());

        log.info("modifyUser request User : {}", modifyUser);

        rttr.addFlashAttribute("message", messageSourceAccessor.getMessage("user.modify"));

        /* 파일업로드 */
        log.info(String.valueOf(profile));
        if (profile.getSize() > 0) {
            /* 상단에 IMAGE_DIR 추가 */
            String filePath = IMAGE_DIR + "profile-images";
            System.out.println("filePath = " + filePath);
            String originFileName = profile.getOriginalFilename();//업로드 파일명
            String ext = originFileName.substring(originFileName.lastIndexOf("."));//업로드 파일명에서 확장자 분리
            String savedName = UUID.randomUUID() + ext;//고유한 파일명 생성 + 확장자 추가

            String finalFilePath = filePath + "/" + savedName;
            System.out.println("finalFilePath = " + finalFilePath);
            File dir = new File(filePath);
            if (!dir.exists()) dir.mkdirs();

            try {
                profile.transferTo(new File(finalFilePath));
                model.addAttribute("savedName", "/upload/profile-images/" + savedName);
            } catch (IOException e) {
                e.printStackTrace();
            }

            String saveFileName = "/upload/profile-images/" + savedName;
            model.addAttribute("modifyUser", modifyUser);
            modifyUser.setProfileImage(saveFileName);
        }

        userService.modifyUser(modifyUser);

        /* 로그인 시 저장 된 Authentication 객체를 변경 된 정보로 교체한다. */
        SecurityContextHolder.getContext().setAuthentication(createNewAuthentication(loginUser.getUserId()));

        log.info("성공");
        return "redirect:/story";
    }


    /* 회원탈퇴 페이지 이동*/
    @GetMapping("/withdrawal")
    public String getUserDeletePage() {
        return "views/user/withdrawal";
    }

    @PostMapping("/delete")
    public String deleteUser(@AuthenticationPrincipal UserDTO user,
                             @RequestParam("password") String password, RedirectAttributes rttr) throws MemberRemoveException {

        log.info("login user : {}", user);
        System.out.println(password);

//        /* 회원을 db에서 삭제 */
//        userService.removeUser(user);
//
//        /* 성공적으로 탈퇴한 경우 메시지를 전달하고 로그인 페이지로 리다이렉트 */
//        rttr.addFlashAttribute("message", "성공적으로 탈퇴되었습니다. 다시 로그인해주세요.");
//
//        /* 로그아웃 처리 (세션만료) */
//        return "redirect:/user/logout";

        // 사용자가 입력한 비밀번호를 인코딩하여 저장된 비밀번호와 비교
        if (passwordEncoder.matches(password, user.getPassword())) {
            /* 회원을 DB에서 삭제 */
            System.out.println("들어가긴하나");
            userService.removeUser(user);

            /* 성공적으로 탈퇴한 경우 메시지를 전달하고 로그인 페이지로 리다이렉트 */
            //rttr.addFlashAttribute("message", "성공적으로 탈퇴되었습니다. 다시 로그인해주세요.");

            /* 로그아웃 처리 (세션 만료) */
            return "redirect:/user/logout";
        } else {
            /* 비밀번호가 일치하지 않는 경우 메시지를 전달하고 회원 탈퇴 페이지로 리다이렉트 */
            rttr.addFlashAttribute("errorMessage", "비밀번호가 일치하지 않습니다. 다시 시도해주세요.");
            return "redirect:/user/withdrawal";
        }
    }

    @PostMapping("/checkPassword")
    public ResponseEntity<Map<String, Boolean>> checkPassword(@RequestBody Map<String, String> requestData,
                                                              @AuthenticationPrincipal UserDTO user) {
        String enteredPassword = requestData.get("password");

        String pwd = userService.getPwd(user.getCode());

        // 비밀번호 일치 여부를 판단하여 결과를 반환합니다.
        boolean isValidPassword = passwordEncoder.matches(enteredPassword, pwd);

        Map<String, Boolean> responseData = new HashMap<>();
        responseData.put("isValidPassword", isValidPassword);

        System.out.println("isValidPassword = " + responseData);

        return ResponseEntity.ok(responseData);
    }

    /* 비밀번호 찾기 페이지 이동 */
    @GetMapping("/findPwd")
    public String findPwdPage() {
        return "views/user/findPwd";
    }
}
