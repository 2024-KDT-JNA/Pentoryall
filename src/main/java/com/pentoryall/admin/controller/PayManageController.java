package com.pentoryall.admin.controller;

import com.pentoryall.admin.service.PayManageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/admin/pay")
public class PayManageController {

    private final PayManageService payManageService;


    public PayManageController(PayManageService payManageService) {
        this.payManageService = payManageService;

    }


    @GetMapping("/settlement")
    public String payAllList(Model model,
                             @RequestParam(defaultValue = "1") int page,
                             @RequestParam(required = false) String searchCondition,
                             @RequestParam(required = false) String searchValue) {
        log.info("payAllList page : {}", page);
        log.info("payAllList searchCondition : {}", searchCondition);
        log.info("payAllList searchValue : {}", searchValue);

        Map<String, String> searchMap = new HashMap<>();
        searchMap.put("searchCondition", searchCondition);
        searchMap.put("searchValue", searchValue);

        Map<String, Object> payListAndPaging = payManageService.payAllList(searchMap, page);
        System.out.println("payListAndPaging = " + payListAndPaging);
        model.addAttribute("paging", payListAndPaging.get("paging"));
        model.addAttribute("payAllList", payListAndPaging.get("payAllList"));

        System.out.println(payListAndPaging.get("payAllList"));

        return "views/admin/adminSettlement";
    }

    @PostMapping("/user/confirm")
    public String getPay(@RequestParam("userCode") Long userCode,
                         RedirectAttributes rttr) {
        System.out.println(userCode);
        int result = payManageService.payConfirmByUserCode((userCode), "APPROVED");
        if (result > 0) {
            rttr.addFlashAttribute("message", "정산처리를 완료했습니다.");
        }

        return "redirect:/admin/pay/settlement";
    }

}