package com.pentoryall.admin.Controller;
import com.pentoryall.admin.Exception.MemberStopException;
import com.pentoryall.admin.Service.CommentReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
        int result =  commentReportService.updateStateByUserCode((userCode), "ACTIVE");
     if (result > 0 ){
         rttr.addFlashAttribute("message", "회원을 성공적으로 해제했습니다.");
     }

        return "redirect:/admin/report/comments";
    }

    @PostMapping("/report/list")
    public String noStopUser(@RequestParam("userCode") Long userCode,
                             RedirectAttributes rttr) throws MemberStopException {

        log.info("{}",userCode);
        // 회원을 정지 상태에서 해제합니다.
        commentReportService.releaseUserById(userCode);

        // 성공적으로 회원을 해제한 경우 메시지를 전달하고 이전 페이지로 리다이렉트합니다.
        rttr.addFlashAttribute("message", "회원을 성공적으로 해제했습니다.");

        return "redirect:/admin/report/list";

    }

}
