package com.pentoryall.post.controller;

import com.pentoryall.common.dto.CommonResponse;
import com.pentoryall.membership.service.MembershipService;
import com.pentoryall.point.enums.TransactionType;
import com.pentoryall.point.service.TransactionService;
import com.pentoryall.post.dto.PostRequestDTO;
import com.pentoryall.post.dto.ValidatePostDTO;
import com.pentoryall.post.service.PostService;
import com.pentoryall.user.dto.UserDTO;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PostApiController {

    private final PostService postService;
    private final TransactionService transactionService;
    private final MembershipService membershipService;


    // 게시글 저장
    @PostMapping("/posts")
    public long savePost(@RequestBody final PostRequestDTO params, HttpSession session) {
        String title = params.getTitle();
        String contents = params.getContent();
        session.setAttribute("title", title);
        session.setAttribute("contents", contents);
        return 0;
    }

    @RequestMapping("/post/validate")
    public ResponseEntity<CommonResponse> validatePost(Long postCode, @AuthenticationPrincipal UserDTO sessionUser) {
        Map<String, Object> response = new HashMap<>();

        // 현재 회원의 게시글이 아니다.
        // - 성인 여부 (인증여부, 나이)
        // - 유료 컨텐츠
        // - 멤버십 컨텐츠
        ValidatePostDTO validatePost = postService.selectPostAndSeriesByPostCode(postCode);
        Long userCode = sessionUser.getCode();
        /* 현재 회원의 게시글이 아님 */
        if (!Objects.equals(validatePost.getUserCode(), userCode)) {
            /* 유료 게시글 유무 */
            if (validatePost.getIsPaid() == 'Y') {
                Long transactionCode = transactionService.existsTransactionCode(userCode, postCode, TransactionType.POST);
                /* 구매 이력이 없음 */
                if (transactionCode == null) {
                    response.put("point", sessionUser.getPoint());
                    response.put("post", validatePost);
                    return ResponseEntity.ok(new CommonResponse(false, "NO_PURCHASE", response));
                }
            }
            /* 멤버십 전용 */
            // if (validatePost.getIsMembershipOnly() == 'Y') {
            //     MembershipDTO selectedMembership = membershipService.selectMembershipByCode(membershipCode);
            //     Long membershipJoinCode = membershipService.existsMembershipJoinCode(userCode, membershipCode, TransactionType.MEMBERSHIP);
            // }
        }

        return ResponseEntity.ok(new CommonResponse(true, null, postCode));
    }
}
