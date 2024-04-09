package com.pentoryall.point.controller;

import com.pentoryall.common.dto.CommonResponse;
import com.pentoryall.membership.dto.MembershipDTO;
import com.pentoryall.membership.service.MembershipService;
import com.pentoryall.point.service.TransactionService;
import com.pentoryall.post.dto.PostDTO;
import com.pentoryall.post.service.PostService;
import com.pentoryall.user.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class PointTransactionController {

    private final TransactionService transactionService;
    private final PostService postService;
    private final MembershipService membershipService;

    @GetMapping("/membership/register")
    public String getRegisterMembershipPage(Long membershipCode, @AuthenticationPrincipal UserDTO sessionUser, Model model) {
        MembershipDTO selectedMembership = membershipService.selectMembershipByCode(membershipCode);
        Long membershipJoinCode = membershipService.existsMembershipJoinCode(membershipCode, sessionUser.getCode());
        if (membershipJoinCode != null) {
            return "redirect:/story";
        }
        return "views/membership/register";
    }

    @PostMapping("/transaction/post")
    public String postTransaction(Long postCode, @AuthenticationPrincipal UserDTO sessionUser) {
        PostDTO post = postService.getPostInformationByPostCode(postCode);
        transactionService.postTransaction(post, sessionUser);

        return "redirect:/post/information?code=" + postCode;
    }

    @PostMapping("/transaction/membership")
    public ResponseEntity<CommonResponse> membershipTransaction(Long membershipCode, @AuthenticationPrincipal UserDTO sessionUser) {

        MembershipDTO membership = membershipService.selectMembershipByCode(membershipCode);
        transactionService.membershipTransaction(membership, sessionUser);

        return null;
    }

}
