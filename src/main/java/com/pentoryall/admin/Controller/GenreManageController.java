package com.pentoryall.admin.Controller;
import com.pentoryall.admin.DTO.GenreManageDTO;
import com.pentoryall.admin.Service.GenreManageService;
import com.pentoryall.user.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/admin")
public class GenreManageController {

    private final GenreManageService genreManageService;

    public GenreManageController(GenreManageService genreManageService) {
        this.genreManageService = genreManageService;
    }


    @GetMapping("/genreList")
    public String getGenrePage(Model model){
        List<GenreManageDTO> genreList = genreManageService.selectAllGenreList();

        model.addAttribute("genreList", genreList);

        return "admin/admin-genre";
    }
}
