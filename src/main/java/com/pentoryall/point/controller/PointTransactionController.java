package com.pentoryall.point.controller;

import com.pentoryall.membership.dto.MembershipDTO;
import com.pentoryall.membership.service.MembershipService;
import com.pentoryall.point.service.TransactionService;
import com.pentoryall.user.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequiredArgsConstructor
public class PointTransactionController {

    private final TransactionService transactionService;

    private final MembershipService membershipService;

    @GetMapping("/point/purchase/list")
    public String purchaseListPage(Model model,
                                   @RequestParam(defaultValue = "1") int page,
                                   @AuthenticationPrincipal UserDTO sessionUser) {

        Map<String, Object> purchaseListAndPaging = transactionService.selectPurchaseAllWithPagingByUserCode(page, sessionUser.getCode());
        model.addAttribute("paging", purchaseListAndPaging.get("paging"));
        model.addAttribute("purchaseList", purchaseListAndPaging.get("purchaseList"));

        return "views/point/purchaseList";
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
