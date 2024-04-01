package com.pentoryall.settlement.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class UserSettlementController {

    @GetMapping("/user/settlement")
    public String getUserSettlement() {
        return "views/user/settlement/add";
    }
}
