package com.pentoryall.order.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/point/order")
public class OrderController {

    @GetMapping
    public String getOrderPage() {
        return "views/point/order";
    }

    @PostMapping
    public String order() {
        return "redirect:/point/order/result";
    }

    @GetMapping("/result")
    public String getOrderResultPage(Model model) {
        // model.addAttribute("message", )
        return "views/point/order";
    }
}