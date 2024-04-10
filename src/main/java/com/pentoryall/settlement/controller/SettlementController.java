package com.pentoryall.settlement.controller;

import com.pentoryall.settlement.dto.SettlementDTO;
import com.pentoryall.settlement.dto.UserSettlementDTO;
import com.pentoryall.settlement.enums.SettlementState;
import com.pentoryall.settlement.service.SettlementService;
import com.pentoryall.settlement.service.UserSettlementService;
import com.pentoryall.user.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequestMapping("/settings")
public class SettlementController {

    private final SettlementService settlementService;
    private final UserSettlementService userSettlementService;

    @Autowired
    public SettlementController(SettlementService settlementService, UserSettlementService userSettlementService) {
        this.settlementService = settlementService;
        this.userSettlementService = userSettlementService;
    }

    @GetMapping("/settlement")
    public String settlementPage(@AuthenticationPrincipal UserDTO sessionUser, Model model) {

        UserSettlementDTO userSettlement = userSettlementService.selectByUserCode(sessionUser.getCode());
        if (userSettlement != null) {
            SettlementDTO settlement = new SettlementDTO();
            settlement.setUserSettlementCode(userSettlement.getCode());
            settlement.setRequestAmount(sessionUser.getRevenue());
            settlement.setActualAmount((sessionUser.getRevenue() / 10) * 9);
            settlement.setState(SettlementState.REQUESTED);

            model.addAttribute("settlement", settlement);
        }

        return "views/settlement/settlement";
    }

    @PostMapping("/settlement")
    public String settlement(@AuthenticationPrincipal UserDTO sessionUser, Model model) {

        UserSettlementDTO userSettlement = userSettlementService.selectByUserCode(sessionUser.getCode());
        if (userSettlement != null) {
            model.addAttribute("userSettlement", userSettlement);
        }

        return "views/settlement/settlement";
    }


    @GetMapping("/settlement/list")
    public String settlementListPage(Model model,
                                     @RequestParam(defaultValue = "1") int page,
                                     @AuthenticationPrincipal UserDTO sessionUser) {
        Map<String, Object> resultMap = settlementService.selectSettlementListWithPagingByUserCode(page, sessionUser.getCode());
        model.addAttribute("paging", resultMap.get("paging"));
        model.addAttribute("settlementList", resultMap.get("settlementList"));

        return "views/settlement/settlementList";
    }

    @GetMapping("/revenue/list")
    public String revenueListPage(Model model,
                                  @RequestParam(defaultValue = "1") int page,
                                  @AuthenticationPrincipal UserDTO sessionUser) {
        Map<String, Object> resultMap = settlementService.selectRevenueListWithPagingByUserCode(page, sessionUser.getCode());
        model.addAttribute("paging", resultMap.get("paging"));
        model.addAttribute("revenueList", resultMap.get("revenueList"));

        return "views/settlement/revenueList";
    }
}

