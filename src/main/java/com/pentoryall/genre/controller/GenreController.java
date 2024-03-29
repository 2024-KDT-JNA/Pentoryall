package com.pentoryall.genre.controller;

import com.pentoryall.genre.dto.GenreDTO;
import com.pentoryall.genre.service.GenreService;
import com.pentoryall.series.dto.SeriesDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/genre")
public class GenreController {

    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping("/genreList")
    public @ResponseBody List<GenreDTO> functionGetGenreList() {
        List<GenreDTO> genreList = genreService.getGenreList();
        System.out.println(genreList);
        return genreList;
    }

    @GetMapping("/lowerGenreList")
    public @ResponseBody List<GenreDTO> functionGetlOWERGenreList() {
        List<GenreDTO> LowergenreList = genreService.getLowerGenreList();
        System.out.println(LowergenreList);
        return LowergenreList;
    }
}
