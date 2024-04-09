package com.pentoryall.point.controller;

import com.pentoryall.common.dto.CommonResponse;
import com.pentoryall.membership.dto.MembershipDTO;
import com.pentoryall.membership.service.MembershipService;
import com.pentoryall.point.service.TransactionService;
import com.pentoryall.post.dto.PostDTO;
import com.pentoryall.post.service.PostService;
import com.pentoryall.user.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transaction")
public class PointTransactionApiController {

    private final TransactionService transactionService;
    private final PostService postService;
    private final MembershipService membershipService;

    @Autowired
    public PointTransactionApiController(TransactionService transactionService, PostService postService, MembershipService membershipService) {
        this.transactionService = transactionService;
        this.postService = postService;
        this.membershipService = membershipService;
    }

    @PostMapping("/post")
    public ResponseEntity<CommonResponse> postTransaction(@RequestBody Long postCode,
                                                          @AuthenticationPrincipal UserDTO sessionUser) {
        PostDTO post = postService.getPostInformationByPostCode(postCode);
        if (sessionUser.getPoint() < post.getPrice()) {
            return ResponseEntity.ok(new CommonResponse(false, "포인트가 부족하여 구매를 실패하였습니다.", null));
        }
        transactionService.postTransaction(post, sessionUser);

        return ResponseEntity.ok(new CommonResponse(true, "구매가 완료되었습니다.", null));
    }

    @PostMapping("/membership")
    public ResponseEntity<CommonResponse> membershipTransaction(@RequestBody Long membershipCode, @AuthenticationPrincipal UserDTO sessionUser) {
        MembershipDTO membership = membershipService.selectMembershipByCode(membershipCode);
        transactionService.membershipTransaction(membership, sessionUser);

        return ResponseEntity.ok(new CommonResponse(true, "가입이 완료되었습니다.", membership.getCode()));
    }
}
