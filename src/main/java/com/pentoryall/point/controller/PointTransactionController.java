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

        // 1. 이미 가입했는가?
        // 2. 가입 후 매달 자동 연장 (한달 단위, 30일)
        // 2.1 해지 신청 시 membership_join 테이블의 end_date가 갱신
        // 2.2 자동 연장 시점에 `보유 포인트 < 멤버십 가격` 시 end_date 갱신 후 해지 처리
        // 2.3 보유 포인트가 충분하다면 transaction 테이블에 포인트 사용 내역이 insert 됨
        MembershipDTO membership = membershipService.selectMembershipByCode(membershipCode);
        transactionService.membershipTransaction(membership, sessionUser);

        return null;
    }
}
