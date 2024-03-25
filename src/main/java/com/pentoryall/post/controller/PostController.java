package com.pentoryall.post.controller;

import com.pentoryall.post.dto.PostDTO;
import com.pentoryall.series.dto.SeriesDTO;
import com.pentoryall.series.service.SeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/post")
public class PostController {

    private final SeriesService seriesService;
    public PostController(SeriesService seriesService) {
        this.seriesService = seriesService;

    }


    @GetMapping("/writer")
    public void writeController(){
    }
    @GetMapping("/list")
    public void listController(){
    }
    @GetMapping("/add")
    public void postAddPageController(){
    }
    @PostMapping("/add")
    public String postAddController(@RequestParam Map<String,String> params){
//            String thumbnailImage = params.get("thumbnail");
//            char isPublic = params.get("isPublic").charAt(0);
            String series = params.get("series");
//        char isFee = params.get("isFee").charAt(0);
//            String neededPoint = params.get("neededPoint");
//        char isAdult = params.get("isAdult").charAt(0);
//            String upperGenre = params.get("upperGenre");
//            String lowerGenre = params.get("lowerGenre");
        System.out.println(series);
           SeriesDTO seriesDTO = seriesService.selectSeriesByTitle(series);
        System.out.println(seriesDTO);
            long seriesCode = seriesDTO.getCode();
        System.out.println("시리즈 코드 :"+seriesCode);

//            postDTO.setIsPublic(isPublic);
//            postDTO.setSeriesCode(series);
//            postDTO.setIsPaid(isFee);
//            postDTO.setPrice(neededPoint);
//            postDTO.setSeriesCode(series);
//            postDTO.setSeriesCode(series);



//        System.out.println(thumbnailImage);
//        System.out.println(isOpen);
//        System.out.println(series);
//        System.out.println(isFee);
//        System.out.println(neededPoint);
//        System.out.println(isAdult);
//        System.out.println(thumbnailImage);
//        System.out.println(upperGenre);
//        System.out.println(lowerGenre);

        return "redirect:/post/list";
    }
}
