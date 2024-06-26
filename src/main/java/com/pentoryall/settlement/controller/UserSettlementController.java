package com.pentoryall.settlement.controller;


import com.pentoryall.settlement.dto.UserSettlementDTO;
import com.pentoryall.settlement.service.UserSettlementService;
import com.pentoryall.user.dto.UserDTO;
import groovy.util.logging.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller

@RequestMapping("/settings/user/settlement")
@RequiredArgsConstructor
public class UserSettlementController {

    private final UserSettlementService userSettlementService;

    private final MessageSourceAccessor messageSourceAccessor;

    @GetMapping
    public String userSettlement(@AuthenticationPrincipal UserDTO sessionUser,
                                 Model model) throws AuthenticationException {
        UserSettlementDTO selectedUserSettlement = userSettlementService.selectByUserCode(sessionUser.getCode());
        if (selectedUserSettlement == null) {
            model.addAttribute("userSettlement", new UserSettlementDTO());
        } else {
            model.addAttribute("userSettlement", selectedUserSettlement);
        }

        model.addAttribute("SETTINGS_ASIDE", "user-settlement");
        return "views/user/settlement/add";
    }

    @PostMapping

    public String saveUserSettlement(@ModelAttribute("userSettlement") UserSettlementDTO modifyUserSettlement,
                                     @AuthenticationPrincipal UserDTO sessionUser,
                                     RedirectAttributes rttr) {

        if (modifyUserSettlement.getUserCode() == null) {
            modifyUserSettlement.setUserCode(sessionUser.getCode());
        }

        userSettlementService.insertNewUserSettlement(modifyUserSettlement);

        rttr.addFlashAttribute("alertMessage", messageSourceAccessor.getMessage("save.success"));

        return "redirect:/settings/user/settlement";
    }
}
