package com.pentoryall.admin.Controller;

import com.pentoryall.admin.Service.CommentReportService;
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
@RequestMapping("/admin/report")
public class CommentReportController {

    private final CommentReportService commentReportService;

    public CommentReportController(CommentReportService commentReportService) {
        this.commentReportService = commentReportService;
    }

    @GetMapping("/comments")
    public String commentReportAllList(Model model,
                                       @RequestParam(defaultValue = "1") int page,
                                       @RequestParam(required = false) String searchCondition,
                                       @RequestParam(required = false) String searchValue
    ) {
        log.info("commentReportList page : {}", page);
        log.info("commentReportList searchCondition : {}", searchCondition);
        log.info("commentReportList searchValue : {}", searchValue);

        Map<String, String> searchMap = new HashMap<>();
        searchMap.put("searchCondition", searchCondition);
        searchMap.put("searchValue", searchValue);

        Map<String, Object> commentReportListAndPaging = commentReportService.selectAllCommentReportList(searchMap, page);
        System.out.println("commentReportListAndPaging = " + commentReportListAndPaging);
        model.addAttribute("paging", commentReportListAndPaging.get("paging"));
        model.addAttribute("commentReportList", commentReportListAndPaging.get("commentReportList"));
        model.addAttribute("commentContents", commentReportListAndPaging.get("commentContents"));

        System.out.println(commentReportListAndPaging.get("commentReportList"));

        return "views/admin/adminCommentReport";
    }

//    회원 정지 해제 및 정지 유지

    @PostMapping("/user/active")
    public String getStop(@RequestParam("userCode") Long userCode,
                          RedirectAttributes rttr) {

        System.out.println("userCode = " + userCode);
        int result = commentReportService.updateStateByUserCode((userCode), "ACTIVE");
        if (result > 0) {
            rttr.addFlashAttribute("message", "회원을 성공적으로 해제했습니다.");
        }

        return "redirect:/admin/report/comments";
    }

    @PostMapping("/comments/delete")
    public String deleteByPostCode(@RequestParam("userCode") Long userCode,
                                   RedirectAttributes rttr) {

        System.out.println("postCode = " + userCode);
        int result = commentReportService.deleteByUserCode(userCode);
        if (result > 0) {
            rttr.addFlashAttribute("message", "댓글신고내역을 성공적으로 삭제했습니다.");
        }

        System.out.println("컨트롤러 userCode : " + userCode);

        return "redirect:/admin/report/comments";
    }


}
