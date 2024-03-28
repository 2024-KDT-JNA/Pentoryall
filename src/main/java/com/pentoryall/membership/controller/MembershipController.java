package com.pentoryall.membership.controller;

import com.pentoryall.membership.dto.MembershipDTO;
import com.pentoryall.membership.service.MembershipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/membership")
public class MembershipController {
    private final MembershipService membershipService;

    @Autowired
    public MembershipController(MembershipService membershipService) {
        this.membershipService = membershipService;
    }

    @PostMapping("/create")
    public String createMembership(Model model, @ModelAttribute MembershipDTO membershipDTO) {
        try {
            //성공할 경우 처리
            MembershipDTO createMembership = membershipService.createMembership(membershipDTO);
            model.addAttribute("message", "멤버십이 성공적으로 개설되었습니다! 이름: " + createMembership.getName());
            model.addAttribute("membershipInfo", createMembership);
            return "/views/membership/successCreate";

        }catch (Exception e){
            //실패할 경우 처리
            model.addAttribute("errorMessage","제시된 양식에 따라 다시 작성해 주세요" + e.getMessage());
            return "/views/membership/failCreate";


        }
    }

    /* 페이지 메핑 */
    @GetMapping("/list")
    public String getMembershipList() {
        return "/views/membership/membershipList";
    }

    @GetMapping("/info")
    public String getMembershipInfo(Model model, @ModelAttribute MembershipDTO membershipDTO) {
        MembershipDTO createMembership = membershipService.createMembership(membershipDTO);
        model.addAttribute("Info", createMembership);

        return "/views/membership/membershipInfo";
    }

    @GetMapping("/create")
    public String createMembership() {
        return "/views/membership/createMembership";
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


