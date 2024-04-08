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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/transaction")
@RequiredArgsConstructor
public class PointTransactionController {

    // TODO
    private final TransactionService transactionService;
    private final PostService postService;
    private final MembershipService membershipService;

    @PostMapping("/post")
    public String postTransaction(Long postCode, @AuthenticationPrincipal UserDTO sessionUser) {
        PostDTO post = postService.getPostInformationByPostCode(postCode);
        transactionService.postTransaction(post, sessionUser);

        return "redirect:/post/information?code=" + postCode;
    }

    @PostMapping("/membership")
    public ResponseEntity<CommonResponse> membershipTransaction(Long membershipCode, @AuthenticationPrincipal UserDTO sessionUser) {
        MembershipDTO membership = membershipService.selectMembershipByCode(membershipCode);
        transactionService.membershipTransaction(membership, sessionUser);

        return null;
    }
}
