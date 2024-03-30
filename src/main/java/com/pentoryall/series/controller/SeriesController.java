package com.pentoryall.series.controller;

import com.pentoryall.genreOfArt.dto.GenreOfArtDTO;
import com.pentoryall.genreOfArt.service.GenreOfArtService;
import com.pentoryall.series.dto.SeriesDTO;
import com.pentoryall.series.service.SeriesService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/series")
public class SeriesController {

    @Value("${image.image-dir}")
    private String IMAGE_DIR;
    private final SeriesService seriesService;
    private final GenreOfArtService genreOfArtService;

    public SeriesController(SeriesService seriesService, GenreOfArtService genreOfArtService) {
        this.seriesService = seriesService;
        this.genreOfArtService = genreOfArtService;
    }

    @GetMapping("/add")
    public String addSeries(){
        return "/views/series/add";
    }
    @GetMapping("/page")
    public String seriesPage(long code,Model model){
        SeriesDTO seriesDTO = seriesService.findSeriesByCode(code);
        model.addAttribute("series",seriesDTO);
        return "/views/series/page";
    }
    @PostMapping("/add")
    public String addSeriesOptions(
            @RequestParam(required = false) MultipartFile thumbnail,
            @ModelAttribute("series") SeriesDTO seriesDTO,
            @RequestParam List<Long> genreCode,
            GenreOfArtDTO genreOfArtDTO,
            Model model
    ){

        System.out.println("seriesDTO = " + seriesDTO);

        seriesDTO.setUserCode(1);

        System.out.println("seriesDTO = " + seriesDTO);
        System.out.println("thumbnailImage = " + thumbnail);
        String savePath="";
        /*파일 가공*/
        if(thumbnail!=null && !thumbnail.isEmpty()) {
            String filePath = IMAGE_DIR + "series-thumbnail-images";

            File dir = new File(filePath);

            if (!dir.exists()) dir.mkdirs();

            String originFileName = thumbnail.getOriginalFilename();//업로드 파일명
            String ext = originFileName.substring(originFileName.lastIndexOf("."));//업로드 파일명에서 확장자 분리
            String savedName = UUID.randomUUID() + ext;//고유한 파일명 생성 + 확장자 추가
            System.out.println("filePath = " + filePath);
            String finalFilePath = filePath + "/" + savedName;
            System.out.println("finalFilePath = " + finalFilePath);

            try {
                thumbnail.transferTo(new File(finalFilePath));
                model.addAttribute("finalFilePath", finalFilePath);
            } catch (IOException e) {
                e.printStackTrace();
            }

            savePath = "/upload/series-thumbnail-images" + "/" + savedName;
        }
        seriesDTO.setThumbnailImage(savePath);

        System.out.println("seriesDTO = " + seriesDTO);

        seriesService.addSeriesOptions(seriesDTO);

        SeriesDTO seriesDTO2 = seriesService.selectRecentSeriesCode();

        long seriesCode = seriesDTO2.getCode();

        System.out.println("seriesCode = " + seriesCode);

        System.out.println("genreCode>>>>>>>>>>>>> = " + genreCode);

        for(int i = 0 ; i<genreCode.size();i++){
            long code = genreCode.get(i);
            genreOfArtDTO.setGenreCode(code);
            genreOfArtDTO.setSeriesCode(seriesCode);
            genreOfArtDTO.setKind("SERIES");
            genreOfArtService.insertSeriesGenre(genreOfArtDTO);
        }
        System.out.println("성공함");
        return "redirect:/series/page?code="+seriesDTO.getCode();
    }
}