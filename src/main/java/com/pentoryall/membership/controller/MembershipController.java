

package com.pentoryall.membership.controller;

import com.pentoryall.membership.dto.MembershipDTO;
import com.pentoryall.membership.service.MembershipService;
import com.pentoryall.user.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/membership")
public class MembershipController {
    private final MembershipService membershipService;

    @Autowired
    public MembershipController(MembershipService membershipService) {
        this.membershipService = membershipService;
    }

    @PostMapping("/create")
    public String createMembership(Model model, @ModelAttribute("membership") MembershipDTO membershipDTO, @AuthenticationPrincipal UserDTO user) {
        try {
            // userDTO 에서 받아올 값을  @AuthenticationPrincipal + 받아올 DTO 받아올 dto ){} 이런식으로 받아올 수 있음
            // @AuthenticationPrincipal : 사용자 인증 및 권한 부여를 처리하는 어노테이션 , 인증받은 사용자의 정보를 컨트롤러나 서비스 등의 스프링 빈에 주입받을 수 있게 해준다.

            membershipDTO.setUserCode(user.getCode());
            // 성공할 경우 처리
            membershipService.createMembership(membershipDTO);
            // 생성된 멤버십 정보를 Model에 추가
            //model.addAttribute("info", createMembership);
            // 성공 메시지를 Model에 추가
            //model.addAttribute("message", "멤버십이 성공적으로 개설되었습니다! 이름: " + createMembership.getName());
            return "/views/membership/successCreate";
        } catch (DataIntegrityViolationException e) {
            // 데이터 무결성 제약 조건 위반 등의 예외 처리
            model.addAttribute("errorMessage", "제시된 양식에 따라 다시 작성해 주세요: " + e.getMessage());
            return "/views/membership/failCreate";
        } catch (Exception e) {
            e.printStackTrace();
            // 기타 예외 처리
            model.addAttribute("errorMessage", "멤버십을 생성하는 중에 오류가 발생했습니다: " + e.getMessage());
            return "/views/membership/failCreate";
        }
    }
/*셀렉트 컨트롤러 */
    @GetMapping("/planInfo")
    public List<MembershipDTO> selectMembershipInfo(){

      return "/views/membership/membershipInfo";
    }

    /* 페이지 메핑 */
    @GetMapping("/list")
    public String getMembershipList() {
        return "/views/membership/membershipList";
    }

//    @GetMapping("/info")
//    public String getMembershipInfo() {
//        return "/views/membership/membershipInfo";
//    }

    @GetMapping("/create")
    public String createMembership() {
        return "/views/membership/createMembership";
    }
    @GetMapping("/view")
    public String getMembershipView(){
        return "/views/membership/infoView";

    }

}


//    @PostMapping("/create")
//    public ResponseEntity<String> createMembership(@RequestBody MembershipDTO membershipDTO){
//        MembershipDTO createMembership = membershipService.createMembership(membershipDTO);
//
//
//        return new ResponseEntity<>("멤버십이 성공적으로 개설되었습니다!" + createMembership.getName(), HttpStatus.CREATED);
//    }
//
//    @PostMapping("/createForm")
//    public String createMembership(
//            @RequestParam("code") Long code,
//            @RequestParam("user_code") Long userCode,
//            @RequestParam("name") String name,
//            @RequestParam("introduction") String introduction,
//            @RequestParam("price") int price,
//            @RequestParam("color") String color,
//            @RequestParam("is_active") boolean isActive,
//            @RequestParam("is_deleted") boolean isDeleted,
//            @RequestParam("create_date") LocalDateTime createDate,
//            @RequestParam("update_date") LocalDateTime updateDate,
//            @RequestParam("delete_date") LocalDateTime deleteDate) {
//
//        System.out.println("코드: " + code);
//        System.out.println("유저 코드: " + userCode);
//        System.out.println("이름: " + name);
//        System.out.println("소개: " + introduction);
//        System.out.println("가격: " + price);
//        System.out.println("색상: " + color);
//        System.out.println("활성화 여부: " + isActive);
//        System.out.println("삭제 여부: " + isDeleted);
//        System.out.println("생성 날짜: " + createDate);
//        System.out.println("수정 날짜: " + updateDate);
//        System.out.println("삭제 날짜: " + deleteDate);
//
//
//      return "redirect:/membership/myMembership";
//    }


