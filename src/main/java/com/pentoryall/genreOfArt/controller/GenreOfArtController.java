package com.pentoryall.genreOfArt.controller;

import com.pentoryall.genre.dto.GenreDTO;
import com.pentoryall.genre.service.GenreService;
import com.pentoryall.genreOfArt.service.GenreOfArtService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/genreOfArt")
public class GenreOfArtController {
    private final GenreService genreService;
    private final GenreOfArtService genreOfArtService;

    public GenreOfArtController(GenreService genreService, GenreOfArtService genreOfArtService) {
        this.genreService = genreService;
        this.genreOfArtService = genreOfArtService;}

    @PostMapping("/lowerGenre")
    public ResponseEntity<List<GenreDTO>> selectLowerGenre(Long genreCode){
    System.out.println("genreCode = " + genreCode);
    List<GenreDTO> genreList = genreService.getLowerGenreList(genreCode);
    return ResponseEntity.ok(genreList);
}

}
