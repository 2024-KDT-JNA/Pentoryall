package com.pentoryall.admin.Controller;
import com.pentoryall.admin.Service.PostReportService;
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
@RequestMapping("/admin/report")
public class PostReportController {

    private final PostReportService postReportService;

    public PostReportController(PostReportService postReportService) {
        this.postReportService = postReportService;
    }


    @GetMapping("/posts")
    public String postReportAllList(Model model,
                                       @RequestParam(defaultValue = "1") int page,
                                       @RequestParam(required = false) String searchCondition,
                                       @RequestParam(required = false) String searchValue
    ) {
        log.info("postReportList page : {}", page);
        log.info("postReportList searchCondition : {}", searchCondition);
        log.info("postReportList searchValue : {}", searchValue);

        Map<String, String> searchMap = new HashMap<>();
        searchMap.put("searchCondition", searchCondition);
        searchMap.put("searchValue", searchValue);

        Map<String, Object> postReportListAndPaging = postReportService.selectAllPostReportList(searchMap, page);
        System.out.println("postReportListAndPaging = " + postReportListAndPaging);
        model.addAttribute("paging", postReportListAndPaging.get("paging"));
        model.addAttribute("postReportList", postReportListAndPaging.get("postReportList"));
        model.addAttribute("postContents", postReportListAndPaging.get("postContents"));

        System.out.println(postReportListAndPaging.get("postReportList"));

        return "views/admin/adminPostReport";
    }


}
