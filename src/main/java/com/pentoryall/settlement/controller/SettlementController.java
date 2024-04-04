package com.pentoryall.settlement.controller;

import com.pentoryall.settlement.service.SettlementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/settings")
@RequiredArgsConstructor
public class SettlementController {

    SettlementService settlementService;

    @GetMapping("/settlement")
    public String settlement() {
        return "views/settlement/settlement";
    }

    @GetMapping("/settlement/list")
    public String settlementListPage() {
        return "views/settlement/settlementList";
    }

    @GetMapping("/revenue/list")
    public String revenueListPage() {
        return "views/settlement/revenueList";
    }
}
