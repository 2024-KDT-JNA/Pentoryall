package com.pentoryall.order.controller;

import com.pentoryall.common.exception.CustomException;
import com.pentoryall.common.exception.order.InvalidOrderInfoException;
import com.pentoryall.common.exception.order.OrderFailedException;
import com.pentoryall.order.dto.OrderDTO;
import com.pentoryall.order.dto.OrderRequestDTO;
import com.pentoryall.order.dto.OrderUserDTO;
import com.pentoryall.order.dto.PaymentDTO;
import com.pentoryall.order.service.OrderService;
import com.pentoryall.user.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

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

    @PostMapping("/payment")
    @ResponseBody
    public Map<String, String> payment(@RequestBody OrderRequestDTO orderRequest,
                                       @AuthenticationPrincipal UserDTO sessionUser) throws CustomException {

        if (isValidUserInfo(orderRequest.getUserCode(), sessionUser.getCode())) {
            throw new InvalidOrderInfoException("올바르지 않은 주문자 정보 입니다.");
        }
        if (orderRequest.getImpUid() == null) {
            throw new InvalidOrderInfoException("주문 정보가 올바르지 않습니다.");
        }

        OrderDTO order = new OrderDTO();
        order.setUserCode(orderRequest.getUserCode());
        order.setAmount(orderRequest.getAmount());
        order.setPoint(orderRequest.getPoint());

        PaymentDTO payment = new PaymentDTO();
        payment.setImpUid(orderRequest.getImpUid());

        orderService.savePointChargeInformation(order, payment);

        // TODO 결제 후 포인트가 안바뀜..ㅠㅠ 이 부분 필요할 듯
        // SecurityContextHolder.getContext().setAuthentication();

        Map<String, String> responseData = new HashMap<>();
        responseData.put("orderCode", order.getCode().toString());

        return responseData;
    }

    @GetMapping("/complete")
    public String orderResultPage(@RequestParam(value = "orderCode") Long code,
                                  @AuthenticationPrincipal UserDTO sessionUser, Model model) {

        OrderDTO order = orderService.selectOrderByCode(code);

        if (isValidUserInfo(order.getUserCode(), sessionUser.getCode())) {
            throw new OrderFailedException("올바르지 않은 주문자 정보 입니다.");
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

}