package com.pentoryall.membership.controller;

import com.pentoryall.membership.dto.MembershipDTO;
import com.pentoryall.membership.dto.MembershipJoinDTO;
import com.pentoryall.membership.service.MembershipService;
import com.pentoryall.user.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
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
            membershipDTO.setUserCode(user.getCode());
            MembershipDTO createdMembership = membershipService.createMembership(membershipDTO); // 생성된 멤버십을 반환 받습니다.
            model.addAttribute("membership", createdMembership); // 생성된 멤버십을 모델에 추가합니다.
            return "views/membership/successCreate";
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", "제시된 양식에 따라 다시 작성해 주세요: " + e.getMessage());
            return "views/membership/failCreate";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", "멤버십을 생성하는 중에 오류가 발생했습니다: " + e.getMessage());
            return "views/membership/failCreate";
        }
    }

    @GetMapping("/planInfo")
    public String selectMembershipInfo(Model model, @AuthenticationPrincipal UserDTO user) {
        try {
            MembershipDTO retrievedMembership = membershipService.selectMembershipByUserCode(user.getCode()); // 멤버십 정보를 가져옵니다.
            if (retrievedMembership != null) {
                model.addAttribute("membership", retrievedMembership); // 가져온 멤버십 정보를 모델에 추가합니다.
                return "views/membership/planInfo";
            } else {
                return "views/membership/membershipInfo"; // 멤버십이 없는 경우에 대한 처리
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", "멤버십 정보를 불러오는 중에 오류가 발생했습니다: " + e.getMessage());
            return "views/membership/errorPage";
        }
    }


    @PostMapping("/modify")
    public String modifyMembership(Model model, @ModelAttribute("membership") MembershipDTO membershipDTO, @AuthenticationPrincipal UserDTO user) {
        try {
            if (membershipDTO != null) {
                membershipDTO.setUserCode(user.getCode());
                membershipService.modifyMembership(membershipDTO);
            } else {
            }
            return "views/membership/successModify"; // 수정이 성공하면 해당 뷰로 이동합니다.
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", "멤버십을 수정하는 중에 오류가 발생했습니다: " + e.getMessage());
            return "redirect:/membership/failModify";
        }
    }

    @PostMapping("/delete")
    public String updateIsDeleted(@RequestParam(name = "membershipCode") long code, Model model) {
        try {
            membershipService.updateIsDeleted(code, 'Y'); // 'Y'를 문자(char)로 전달합니다.
            MembershipDTO membershipDTO = membershipService.selectMembershipByCode(code);
            model.addAttribute("membership", membershipDTO); // "membership" 대신 "membershipDTO" 사용
            return "/views/membership/successDelete";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", "멤버십 탈퇴 과정 중에 오류가 발생하였습니다. : " + e.getMessage());
            return "/views/membership/failDelete";
        }
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
        System.out.println(membershipJoinList);

        model.addAttribute("membershipJoinList", membershipJoinList);

        if (membershipJoinList.isEmpty()) {
            return "/views/membership/noMembershipJoinList";
        } else {
            return "/views/membership/membershipJoinList";
        }



//
//
//
//
//
//        List<MembershipDTO> membershipCode = new ArrayList<>();
//
//        for(int i = 0 ; i<SubscribedList.size();i++) {
//            MembershipDTO membership = membershipService.selectMembershipByMembershipCode(SubscribedList.get(i).getMembershipCode());
//            membershipCode.add(membership);
//        }
//

//
//        System.out.println("membershipCode = " + membershipCode);
//
//        return "/views/membership/membershipJoinList";
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

