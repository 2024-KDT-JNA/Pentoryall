package com.pentoryall.point.controller;

import com.pentoryall.point.service.PointTransactionService;
import com.pentoryall.user.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping()
@RequiredArgsConstructor
public class PointTransactionController {

    // TODO
    // 나중에. 구매된 컨텐츠의 경우는 알려줘야됨..조인?
    // 멤버십도 스토리 같은 곳 가면 이미 구매내역있으면 알려줘야되네..
    // private final     PostService postService;
    // private final     PostService postService;
    private final PointTransactionService pointTransactionService;

    public ResponseEntity<String> purchase(Long postCode,
                                           @AuthenticationPrincipal UserDTO sessionUser) {
        // isAlreadyPurchaseByPostCode

        return null;
    }
    //     작품 결제
    // 이미 구매 내역이 있으면 : AlreadyHavePurchaseHistoryException?
    // 아니면. 구매 진행~

    //     멤버십 구매
    //     request: 회원정보, 멤버십 코드, 가격?
    // 검증: 이미 가입 시 .. 이미 가입되있음을 알려주는 response
    // 서비스:

    //

    // 포인트 거래 내역

    // 작품 구매 내역
}
