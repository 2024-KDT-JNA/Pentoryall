package com.pentoryall.admin.controller;

import com.pentoryall.admin.dto.GenreManageDTO;
import com.pentoryall.admin.service.GenreManageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/admin/genre")
public class GenreManageController {

    private final GenreManageService genreManageService;

    public GenreManageController(GenreManageService genreManageService) {
        this.genreManageService = genreManageService;
    }


    @GetMapping("/list")
    public String getGenrePage(Model model) {
        List<GenreManageDTO> genreList = genreManageService.selectFirstGenreList();

        model.addAttribute("genreList", genreList);
        System.out.println("컨트롤러 genrelist" + genreList);

        List<GenreManageDTO> genreSecondList = genreManageService.selectSecondGenreList();

        model.addAttribute("genreSecondList", genreSecondList);
        System.out.println("컨트롤러 genreSecondList" + genreSecondList);


        return "views/admin/adminGenre";
    }

    @PostMapping("/add")
    public String addGenre(@RequestParam("genreName") String name) {

        genreManageService.addFirstGenre(name);

        return "redirect:/admin/genre/list";
    }

    @PostMapping("/addSecond")
    public String addSecondGenre(@RequestParam("genreName") String name) {

        genreManageService.addSecondGenre(name);

        return "redirect:/admin/genre/list";
    }


    @PostMapping("/delete")
    public String deleteGenre(@RequestParam("name") String name) {
        genreManageService.deleteSecondGenre(name);
        return "redirect:/admin/genre/list";
    }

    @PostMapping("/modifyFirstGenre")
    public String modifyGenre(
            @RequestParam("genreName") String name) {
        GenreManageDTO genreDTO = new GenreManageDTO();
        genreDTO.setName(name);
        //        genreDTO.setCode(code);
        genreManageService.modifyFirstGenre(genreDTO);

        return "redirect:/admin/genre/list";

    }
}



