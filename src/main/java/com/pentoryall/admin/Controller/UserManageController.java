package com.pentoryall.admin.Controller;
import com.pentoryall.admin.DTO.UserManageDTO;
import com.pentoryall.admin.Service.UserManageService;
import com.pentoryall.user.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.Pageable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Slf4j
@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class UserManageController {

    private final UserManageService userManageService;

    @GetMapping("/list")
    public String getAdminPage(Model model,
                               @RequestParam(defaultValue = "1") int page,
                               @RequestParam(required = false) String searchCondition,
                               @RequestParam(required = false) String searchValue
                               ){
        log.info("userList page : {}", page);
        log.info("userList searchCondition : {}", searchCondition);
        log.info("userList searchValue : {}", searchValue);

        Map<String, String> searchMap = new HashMap<>();
        searchMap.put("searchCondition", searchCondition);
        searchMap.put("searchValue", searchValue);

        Map<String, Object> userListAndPaging = userManageService.selectAllUserList(searchMap, page);
        model.addAttribute("paging", userListAndPaging.get("paging"));
        model.addAttribute("userList", userListAndPaging.get("userList"));

        return "views/admin/adminUser";
    }


}
