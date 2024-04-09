package com.pentoryall.point.controller;

import com.pentoryall.membership.dto.MembershipDTO;
import com.pentoryall.membership.service.MembershipService;
import com.pentoryall.user.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PointTransactionController {

    private final MembershipService membershipService;

    @Autowired
    public PointTransactionController(MembershipService membershipService) {
        this.membershipService = membershipService;
    }

    @GetMapping("/membership/register")
    public String getRegisterMembershipPage(Long membershipCode, @AuthenticationPrincipal UserDTO sessionUser, Model model) {
        MembershipDTO selectedMembership = membershipService.selectMembershipByCode(membershipCode);
        Long membershipJoinCode = membershipService.existsMembershipJoinCode(membershipCode, sessionUser.getCode());
        if (membershipJoinCode != null) {
            return "redirect:/story";
        }
        return "views/membership/register";
    }
}
