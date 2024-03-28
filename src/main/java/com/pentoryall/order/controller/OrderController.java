package com.pentoryall.order.controller;

import com.pentoryall.common.exception.order.OrderFailedException;
import com.pentoryall.order.dto.OrderDTO;
import com.pentoryall.order.dto.PaymentDTO;
import com.pentoryall.order.dto.PaymentRequestDTO;
import com.pentoryall.order.dto.PaymentResponseDTO;
import com.pentoryall.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public String orderPage() {
        return "views/point/order";
    }

//    @PostMapping
//    public String order() {
//        return "redirect:/order";
//    }
//
//
//    @PostMapping
//    public String paymentPage() {
//        return "redirect:/order/payment";
//    }

    @PostMapping("/payment")
    @ResponseBody
    public ResponseEntity<PaymentResponseDTO> payment(@RequestBody PaymentRequestDTO paymentRequest) throws OrderFailedException {
        if (paymentRequest.getImpUid() == null) {
            throw new OrderFailedException("주문 정보가 존재하지 않습니다.");
        }

        OrderDTO order = new OrderDTO(paymentRequest);
        PaymentDTO payment = new PaymentDTO(paymentRequest);
        orderService.save(order, payment);

        PaymentResponseDTO paymentResponse = new PaymentResponseDTO();
        paymentResponse.setOrderCode(order.getCode());


        return ResponseEntity.ok(paymentResponse);
    }

    @GetMapping("/complete")
    public String orderResultPage(@RequestParam String success,
                                  @RequestParam(value = "orderCode", required = false) Long orderCode,
                                  Model model) {
        model.addAttribute("order", orderService.findByOrderCode(orderCode));
        return "views/point/orderResult";
    }


    @PostMapping("/complete")
    public String orderResult(@RequestParam String success,
                              @RequestParam(value = "orderCode", required = false) Long orderCode,
                              Model model) {
        model.addAttribute("order", orderService.findByOrderCode(orderCode));
        return "views/point/orderResult";
    }
}