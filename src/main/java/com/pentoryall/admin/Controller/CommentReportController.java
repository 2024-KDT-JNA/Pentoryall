package com.pentoryall.admin.Controller;
import com.pentoryall.admin.Service.CommentReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/admin")
public class CommentReportController {

    private final CommentReportService commentReportService;

    public CommentReportController(CommentReportService commentReportService) {
        this.commentReportService = commentReportService;
    }

    @GetMapping("/report/list")
    public String commentReportAllList(Model model,
                                       @RequestParam(defaultValue = "1") int page,
                                       @RequestParam(required = false) String searchCondition,
                                       @RequestParam(required = false) String searchValue
    ){
        log.info("commentReportList page : {}", page);
        log.info("commentReportList searchCondition : {}", searchCondition);
        log.info("commentReportList searchValue : {}", searchValue);

        Map<String, String> searchMap = new HashMap<>();
        searchMap.put("searchCondition", searchCondition);
        searchMap.put("searchValue", searchValue);

        Map<String, Object> commentReportListAndPaging = commentReportService.selectAllCommentReportList(searchMap, page);
        model.addAttribute("paging", commentReportListAndPaging.get("paging"));
        model.addAttribute("commentReportList", commentReportListAndPaging.get("commentReportList"));

        System.out.println(commentReportListAndPaging.get("commentReportList"));

        return "views/admin/adminCommentReport";
    }
}
