package com.pentoryall.settlement.controller;

import com.pentoryall.settlement.dto.SettlementDTO;
import com.pentoryall.settlement.dto.UserSettlementDTO;
import com.pentoryall.settlement.service.SettlementService;
import com.pentoryall.settlement.service.UserSettlementService;
import com.pentoryall.user.dto.UserDTO;
import com.pentoryall.user.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

@Controller
@RequestMapping("/settings")
public class SettlementController {

    private final AuthService authService;
    private final SettlementService settlementService;
    private final UserSettlementService userSettlementService;

    @Autowired
    public SettlementController(AuthService authService, SettlementService settlementService, UserSettlementService userSettlementService) {
        this.authService = authService;
        this.settlementService = settlementService;
        this.userSettlementService = userSettlementService;
    }

    @GetMapping("/settlement")
    public String settlementPage(@AuthenticationPrincipal UserDTO sessionUser, Model model) {

        UserSettlementDTO userSettlement = userSettlementService.selectByUserCode(sessionUser.getCode());
        if (userSettlement != null) {
            SettlementDTO settlement = new SettlementDTO();
            settlement.setUserSettlementCode(userSettlement.getCode());

            model.addAttribute("settlement", settlement);
        }

        return "views/settlement/settlement";
    }

    @PostMapping("/settlement")
    public String settlement(@ModelAttribute SettlementDTO settlement,
                             @AuthenticationPrincipal UserDTO sessionUser,
                             RedirectAttributes rttr) {

        if (settlement.getRequestAmount() > sessionUser.getRevenue()) {
            rttr.addFlashAttribute("alertMessage", "요청 금액 오류");
            return "redirect:/settings/settlement";
        }

        SettlementDTO saveSettlement = new SettlementDTO(settlement.getUserSettlementCode(), settlement.getRequestAmount());
        settlementService.insertSettlementRequest(sessionUser.getCode(), saveSettlement);

        SecurityContextHolder.getContext().setAuthentication(createNewAuthentication(sessionUser.getUserId()));

        rttr.addFlashAttribute("alertMessage", "정산 신청이 완료되었습니다.");
        return "redirect:/settings/settlement/list";
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


    // TODO 중복코드 차후에 합치기
    protected Authentication createNewAuthentication(String userId) {
        UserDetails newPrincipal = authService.loadUserByUsername(userId);
        return new UsernamePasswordAuthenticationToken(newPrincipal, newPrincipal.getPassword(), newPrincipal.getAuthorities());
    }
}

