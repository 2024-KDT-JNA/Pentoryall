package com.pentoryall.point.controller;

import com.pentoryall.common.dto.CommonResponse;
import com.pentoryall.common.exception.PageNotFoundException;
import com.pentoryall.point.dto.OrderDTO;
import com.pentoryall.point.dto.OrderRequestDTO;
import com.pentoryall.point.dto.OrderUserDTO;
import com.pentoryall.point.dto.PaymentDTO;
import com.pentoryall.point.service.OrderService;
import com.pentoryall.user.dto.UserDTO;
import com.pentoryall.user.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/point/order")
public class PointOrderController {

    private final OrderService orderService;

    private final AuthService authService;

    @GetMapping
    public String orderPage(@AuthenticationPrincipal UserDTO sessionUser, Model model) {

        OrderUserDTO user = new OrderUserDTO();

        if (sessionUser != null) {
            user.setCode(sessionUser.getCode());
            user.setUserId(sessionUser.getUserId());
            user.setName(sessionUser.getName());
            user.setEmail(sessionUser.getEmail());
            user.setPoint(sessionUser.getPoint());
        }

        model.addAttribute("user", user);

        return "views/point/order";
    }

    @GetMapping("/list")
    public String orderListPage(Model model,
                                @RequestParam(defaultValue = "1") int page,
                                @RequestParam(required = false) String searchCondition,
                                @RequestParam(required = false) String searchValue,
                                @AuthenticationPrincipal UserDTO sessionUser) {

        Map<String, String> searchMap = new HashMap<>();
        searchMap.put("searchCondition", searchCondition);
        searchMap.put("searchValue", searchValue);

        Map<String, Object> orderListAndPaging = orderService.selectAllWithPagingByUserCode(searchMap, page, sessionUser.getCode());
        model.addAttribute("paging", orderListAndPaging.get("paging"));
        model.addAttribute("orderList", orderListAndPaging.get("orderList"));

        return "views/point/orderList";
    }

    @PostMapping("/payment")
    @ResponseBody
    public ResponseEntity<CommonResponse> payment(@RequestBody OrderRequestDTO orderRequest,
                                                  @AuthenticationPrincipal UserDTO sessionUser) {
        CommonResponse response = null;

        if (isValidUserInfo(orderRequest.getUserCode(), sessionUser.getCode())) {
            response = new CommonResponse(false, "올바르지 않은 주문자 정보 입니다.", null);
        }
        if (orderRequest.getImpUid() == null) {
            response = new CommonResponse(false, "주문 정보가 올바르지 않습니다.", null);
        }

        OrderDTO order = new OrderDTO();
        order.setUserCode(orderRequest.getUserCode());
        order.setAmount(orderRequest.getAmount());
        order.setPoint(orderRequest.getPoint());

        PaymentDTO payment = new PaymentDTO();
        payment.setImpUid(orderRequest.getImpUid());

        orderService.savePointChargeInformation(order, payment);

        // 세션 정보 재설정
        SecurityContextHolder.getContext().setAuthentication(createNewAuthentication(sessionUser.getUserId()));

        response = new CommonResponse(true, null, order.getCode());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/complete")
    public String orderResultPage(@RequestParam(value = "orderCode") Long code,
                                  @AuthenticationPrincipal UserDTO sessionUser, Model model) {

        OrderDTO order = orderService.selectOrderByCode(code);

        if (isValidUserInfo(order.getUserCode(), sessionUser.getCode())) {
            throw new PageNotFoundException();
        }

        model.addAttribute("orderCode", order.getCode());
        model.addAttribute("paidPoint", order.getAmount());
        model.addAttribute("paidAmount", order.getPoint());
        model.addAttribute("paidAt", order.getCreateDate());

        return "views/point/orderResult";
    }


    /* 주문 정보의 회원 코드와 로그인 회원 정보가 일치하는지 검증 */
    private boolean isValidUserInfo(Long userCode, Long sessionUserCode) {
        return userCode == null || !Objects.equals(userCode, sessionUserCode);
    }

    // TODO 중복코드 차후에 합치기
    protected Authentication createNewAuthentication(String userId) {
        UserDetails newPrincipal = authService.loadUserByUsername(userId);
        return new UsernamePasswordAuthenticationToken(newPrincipal, newPrincipal.getPassword(), newPrincipal.getAuthorities());
    }
}
