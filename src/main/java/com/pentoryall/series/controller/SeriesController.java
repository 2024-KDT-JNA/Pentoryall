package com.pentoryall.series.controller;

import com.pentoryall.series.dto.SeriesDTO;
import com.pentoryall.series.service.SeriesService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/series")
public class SeriesController {

    @Value("${image.image-dir}")
    private String IMAGE_DIR;
    private final SeriesService seriesService;

    public SeriesController(SeriesService seriesService) {
        this.seriesService = seriesService;
    }

    @GetMapping("/add")
    public void addSeries(){
    }
    @GetMapping("/page")
    public String seriesPage(long code,Model model){
        System.out.println("code++++++ = " + code);
        SeriesDTO seriesDTO = seriesService.findSeriesByCode(code);
        System.out.println("seriesDTO >>>>>>>= " + seriesDTO);
        model.addAttribute("series",seriesDTO);
        return "/series/page";
    }
    @PostMapping("/add")
    public String addSeriesOptions(
            @RequestParam(required = false) MultipartFile thumbnail,
            @ModelAttribute("series") SeriesDTO seriesDTO,
            Model model
    ){

        System.out.println("seriesDTO = " + seriesDTO);

        seriesDTO.setUserCode(1);

        System.out.println("seriesDTO = " + seriesDTO);
        System.out.println("thumbnailImage = " + thumbnail);

        /*파일 가공*/
        String filePath = IMAGE_DIR + "series-thumbnail-images";

        File dir = new File(filePath);

        if (!dir.exists()) dir.mkdirs();

        String originFileName = thumbnail.getOriginalFilename();//업로드 파일명
        String ext = originFileName.substring(originFileName.lastIndexOf("."));//업로드 파일명에서 확장자 분리
        String savedName = UUID.randomUUID() + ext;//고유한 파일명 생성 + 확장자 추가
        System.out.println("filePath = " + filePath);
        String finalFilePath = filePath  +"/"+ savedName;
        System.out.println("finalFilePath = " + finalFilePath);

        try {
            thumbnail.transferTo(new File(finalFilePath));
            model.addAttribute("finalFilePath", finalFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String saveFileName = "/upload/series-thumbnail-images"+"/"+savedName;

        seriesDTO.setThumbnailImage(saveFileName);

        System.out.println("seriesDTO = " + seriesDTO);

        long seriesCode = seriesService.addSeriesOptions(seriesDTO);

        System.out.println("seriesCode = " + seriesCode);

        return "redirect:/series/page?code="+seriesDTO.getCode();
    }
}