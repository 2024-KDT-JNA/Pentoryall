package com.pentoryall.membership.controller;

import com.pentoryall.membership.dto.MembershipDTO;
import com.pentoryall.membership.dto.MembershipJoinDTO;
import com.pentoryall.membership.service.MembershipService;
import com.pentoryall.user.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
    public String createMembership(Model model, @ModelAttribute("membership") MembershipDTO membershipDTO,
                                   @AuthenticationPrincipal UserDTO user) {
        System.out.println(user.getCode());
        membershipDTO.setUserCode(user.getCode());
        membershipService.createMembership(membershipDTO); // 생성된 멤버십을 반환 받습니다.

        System.out.println(membershipDTO);
        if (membershipDTO.getCode() <= 0) {
            model.addAttribute("errorMessage", "제시된 양식에 따라 다시 작성해 주세요: ");
        } else {
            model.addAttribute("membership", membershipDTO); // 생성된 멤버십을 모델에 추가합니다.
        }
        return "/views/membership/resultCreate";
    }

    @PostMapping("/modify")
    public String modifyMembership(Model model, @ModelAttribute("membership") MembershipDTO membershipDTO, @AuthenticationPrincipal UserDTO user) {
        membershipDTO.setUserCode(user.getCode());
        membershipService.modifyMembership(membershipDTO);
        if (membershipDTO.getCode() <= 0) {
            model.addAttribute("errorMessage", "멤버십을 수정하는 중에 오류가 발생했습니다: ");
        } else {
            model.addAttribute("membership", membershipDTO); // 생성된 멤버십을 모델에 추가합니다.

        } return "views/membership/resultModify"; // 수정이 성공하면 해당 뷰로 이동합니다.
    }

    @PostMapping("/delete")
    public String updateIsDeleted(@RequestParam(name = "membershipCode") long code, Model model) {

        membershipService.updateIsDeleted(code, 'Y'); // 'Y'를 문자(char)로 전달합니다.
        MembershipDTO membershipDTO = membershipService.selectMembershipByCode(code);
        model.addAttribute("membership", membershipDTO); // "membership" 대신 "membershipDTO" 사용
        if (membershipDTO == null)
            model.addAttribute("errorMessage", "멤버십 탈퇴 과정 중에 오류가 발생하였습니다. :");
        else {
        }   return "/views/membership/resultDelete";
    }


    @GetMapping("/delete")
    @PreAuthorize("isAuthenticated()")
    public String deactivateMembership(@AuthenticationPrincipal UserDTO user, Model model) {
        long userCode = user.getCode();
        membershipService.updateIsDeleted(userCode, 'Y'); // 'Y'를 문자(char)로 전달합니다.
        MembershipDTO membershipDTO = membershipService.selectMembershipByUserCode(userCode);
        model.addAttribute("membership", membershipDTO);
        model.addAttribute("message", "멤버십이 성공적으로 비활성화되었습니다.");
        return "/views/membership/deleteMembership";
    }

    @GetMapping("/membershipJoinList")
    public String getMembershipJoinList(Model model, @AuthenticationPrincipal UserDTO user) {
        List<MembershipJoinDTO> membershipJoinList = membershipService.selectAllMembershipJoinList(user.getCode());
        model.addAttribute("membershipJoinList", membershipJoinList);
        if (membershipJoinList.isEmpty()) {
            return "/views/membership/noMembershipJoinList";
        } else {
            return "/views/membership/membershipJoinList";
        }
    }

    @GetMapping("/joinMemberList")
    public String getJoinMemberList(Model model, @AuthenticationPrincipal UserDTO user) {
        List<MembershipJoinDTO> joinMemberList = membershipService.selectAllJoinMemberList(user.getCode());
        model.addAttribute("joinMemberList", joinMemberList);
        if ((joinMemberList.isEmpty())) {
            return "/views/membership/noJoinMemberList";
        } else {
            return "/views/membership/joinMemberList";
        }
    }


    @GetMapping("/noList")
    public String getNoMembershipJoinList() {
        return "/views/membership/noMembershipJoinList";
    }

    @GetMapping("/info")
    public String getMembershipInfo() {
        return "/views/membership/membershipInfo";
    }

    @GetMapping("/create")
    public String createMembership() {
        return "/views/membership/createMembership";
    }

    @GetMapping("/modify")
    public String modifyMembership() {
        return "/views/membership/membershipModify";

    }
}
//    @GetMapping("/planInfo")
//    public String selectMembershipInfo(Model model, @AuthenticationPrincipal UserDTO user) {
//        try {
//            MembershipDTO retrievedMembership = membershipService.selectMembershipByUserCode(user.getCode()); // 멤버십 정보를 가져옵니다.
//            if (retrievedMembership != null) {
//                model.addAttribute("membership", retrievedMembership); // 가져온 멤버십 정보를 모델에 추가합니다.
//                return "views/membership/planInfo";
//            } else {
//                return "views/membership/membershipInfo"; // 멤버십이 없는 경우에 대한 처리
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            model.addAttribute("errorMessage", "멤버십 정보를 불러오는 중에 오류가 발생했습니다: " + e.getMessage());
//            return "views/membership/errorPage";
//        }
//    }
//    @GetMapping("userProfile/membership/{subscribeUserCode}")
//    public String getUserProfile(@PathVariable("subscribeUserCode") long subscribeUserCode, Model model) {
//        MembershipDTO membershipDTO = membershipService.selectMembershipUserProfileBySubscribeUserCode(subscribeUserCode); // membershipService에서 해당 유저의 멤버십 정보 가져오기
//        model.addAttribute("membershipDTO", membershipDTO); // 모델에 membershipDTO 추가
//        System.out.println("subscribeUserCode = " + subscribeUserCode);
//        return "views/userProfile/membershipInfo"; // 해당 뷰 반환
//    }

