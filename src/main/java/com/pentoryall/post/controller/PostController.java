package com.pentoryall.post.controller;

import com.pentoryall.post.dto.PostDTO;
import com.pentoryall.post.service.PostService;
import com.pentoryall.series.dto.SeriesDTO;
import com.pentoryall.series.service.SeriesService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/post")
public class PostController {

    private final SeriesService seriesService;
    private final PostService postService;

    public PostController(SeriesService seriesService, PostService postService) {
        this.seriesService = seriesService;
        this.postService = postService;
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
    public String postAddController(@RequestParam Map<String, String> params, PostDTO postDTO) {
        String title = params.get("title");
        String contents = params.get("contents");
        String thumbnailImage = params.get("thumbnail");
        char isPublic = params.get("isPublic").charAt(0);
        String series = params.get("series");
        char isFee = params.get("isFee").charAt(0);
        long neededPoint = Long.parseLong(params.get("neededPoint"));
        char isAdult = params.get("isAdult").charAt(0);

        /*파일 가공 로직*/


        SeriesDTO seriesDTO = seriesService.selectSeriesByTitle(series);
        System.out.println(seriesDTO);
        long seriesCode = seriesDTO.getCode();
        System.out.println("시리즈 코드 :" + seriesCode);

        postDTO.setTitle(title);
        postDTO.setContent(contents);
        postDTO.setIsPublic(isPublic);
        postDTO.setSeriesCode(seriesCode);
        postDTO.setIsPaid(isFee);
        postDTO.setPrice(neededPoint);
        postDTO.setIsAdult(isAdult);

        System.out.println("postDTO:"+postDTO);
        postService.insertPost(postDTO);

        return "redirect:/post/list";
    }
}
