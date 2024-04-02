package com.pentoryall.admin.Controller;

import com.pentoryall.admin.Service.ModifyPostService;
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
@RequestMapping("/admin/modify")
public class ModifyPostController {

    private final ModifyPostService modifyPostService;

    public ModifyPostController(ModifyPostService modifyPostService) {
        this.modifyPostService = modifyPostService;
    }

    @GetMapping("/posts")
    public String modifyPostAllList(Model model,
                                    @RequestParam(defaultValue = "1") int page,
                                    @RequestParam(required = false) String searchCondition,
                                    @RequestParam(required = false) String searchValue) {
        log.info("modifyPostList page : {}", page);
        log.info("modifyPostAllList searchCondition : {}", searchCondition);
        log.info("modifyPostAllList searchValue : {}", searchValue);

        Map<String, String> searchMap = new HashMap<>();
        searchMap.put("searchCondition", searchCondition);
        searchMap.put("searchValue", searchValue);

        Map<String, Object> modifyPostListAndPaging = modifyPostService.selectAllModifyList(searchMap, page);
        System.out.println("modifyPostListAndPaging = " + modifyPostListAndPaging);
        model.addAttribute("paging", modifyPostListAndPaging.get("paging"));
        model.addAttribute("modifyPostAllList", modifyPostListAndPaging.get("modifyPostAllList"));
        model.addAttribute("modifyPostContent", modifyPostListAndPaging.get("modifyPostContent"));

        System.out.println(modifyPostListAndPaging.get("modifyPostAllList"));

        return "views/admin/adminPostModify";

    }

    @PostMapping("/posts/confirm")
    public String confirmByPostCode(@RequestParam("postCode") Long postCode,
                                    RedirectAttributes rttr) {
        System.out.println("postCode = " + postCode);
        int result = modifyPostService.modifyByPostCode((postCode),"confirmContent");
        if (result > 0) {
            rttr.addAttribute("message", "포스트를 성공적으로 수정했습니다.");

        }
        System.out.println("컨트롤러 postCode : " + postCode);

        return "redirect:/admin/modify/posts";
    }

}
