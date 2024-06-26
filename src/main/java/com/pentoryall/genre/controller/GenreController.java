package com.pentoryall.genre.controller;

import com.pentoryall.comment.dto.CommentDetailDTO;
import com.pentoryall.genre.dto.GenreDTO;
import com.pentoryall.genre.service.GenreService;
import com.pentoryall.genreOfArt.dto.GenreOfArtDTO;
import com.pentoryall.genreOfArt.service.GenreOfArtService;
import com.pentoryall.series.dto.SeriesDTO;
import com.pentoryall.series.service.SeriesService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/genre")
public class GenreController {

    private final GenreService genreService;
    private final SeriesService seriesService;
    private final GenreOfArtService genreOfArtService;

    public GenreController(GenreService genreService, SeriesService seriesService, GenreOfArtService genreOfArtService) {
        this.genreService = genreService;
        this.seriesService = seriesService;
        this.genreOfArtService = genreOfArtService;
    }

    @GetMapping("/genreList")
    public @ResponseBody List<GenreDTO> functionGetGenreList() {
        List<GenreDTO> genreList = genreService.getGenreList();
        System.out.println(genreList);
        return genreList;
    }


    @GetMapping("/lowerGenreList")
    public @ResponseBody List<GenreDTO> functionGetLowerGenreList(long code) {
        List<GenreDTO> LowergenreList = genreService.getLowerGenreList(code);
        System.out.println(LowergenreList);
        return LowergenreList;
    }

    @GetMapping("/upperGenre")
    public @ResponseBody List<GenreDTO> functionGetUpperGenre(@RequestParam long code) {
        List<GenreDTO> genreDTO = new ArrayList<>();
        if (code != 1) {
            GenreOfArtDTO genreOfArtDTO = genreOfArtService.getGenre(code);
            System.out.println("genreOfArtDTO = " + genreOfArtDTO);
            long genreCode = genreOfArtDTO.getGenreCode();
            System.out.println("genreCode = " + genreCode);
            genreDTO = genreService.selectGenreList(genreCode);
            System.out.println("genreDTO = " + genreDTO);
        }
        return genreDTO;
    }

    @GetMapping("/lowerGenre")
    public @ResponseBody List<GenreDTO> functionGetLowerGenre(@RequestParam long code) {
        List<GenreOfArtDTO> genreOfArtDTO = genreOfArtService.getLowerGenre(code);
        List<GenreDTO> genreDTO = new ArrayList<>();
        for (int i = 0; i < genreOfArtDTO.size(); i++) {
            long genreCode = genreOfArtDTO.get(i).getGenreCode();
            GenreDTO genreDTO1 = genreService.selectGenre(genreCode);
            genreDTO.add(genreDTO1);
        }
        return genreDTO;
    }

    @GetMapping("/selectedGenre")
    public @ResponseBody List<GenreDTO> functionGetSelectedGenre(@RequestParam long code) {
        List<GenreOfArtDTO> genreOfArtDTOList = genreOfArtService.findGenreBySeriesCodeSeries(code);
        System.out.println("genreOfArtDTOList = " + genreOfArtDTOList);
        List<GenreDTO> genreDTO = new ArrayList<>();
        for (int i = 0; i < genreOfArtDTOList.size(); i++) {
            long genreCode = genreOfArtDTOList.get(i).getGenreCode();
            GenreDTO genreDTO1 = genreService.selectGenre(genreCode);
            genreDTO.add(genreDTO1);
        }
        System.out.println("genreDTO .....= " + genreDTO);
        return genreDTO;
    }

    @GetMapping("/series")
    public ResponseEntity<List<SeriesDTO>> selectSeriesList(GenreDTO genreDTO){
        System.out.println("genreDTO = " + genreDTO);
        List<GenreOfArtDTO> seriesList = genreOfArtService.selectSeriesByGenre(genreDTO.getCode());

        List<Long> seriesCodeList = new ArrayList<>();
        for(int i = 0 ; i<seriesList.size();i++){
            seriesCodeList.add(seriesList.get(i).getSeriesCode());
        }

        List<SeriesDTO> result = new ArrayList<>();
        for(int i = 0 ; i<seriesCodeList.size() ; i++){
            SeriesDTO seriesDTO = seriesService.getSeriesInformationBySeriesCode(seriesCodeList.get(i));
            result.add(seriesDTO);
        }

        System.out.println("result = " + result);
        return ResponseEntity.ok(result);
    }

}
