package com.pentoryall.common.controller;

import com.pentoryall.post.dto.PostDTO;
import com.pentoryall.post.service.PostService;
import com.pentoryall.series.dto.SeriesDTO;
import com.pentoryall.series.service.SeriesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class MainController {

    private final SeriesService seriesService;
    private final PostService postService;

    public MainController(SeriesService seriesService, PostService postService) {
        this.seriesService = seriesService;
        this.postService = postService;
    }

    @RequestMapping({ "/", "/main", "/index" })
    public String defaultLocation(Model model) {
        List<SeriesDTO> seriesDTO = seriesService.selectSeriesList();
        model.addAttribute("seriesList",seriesDTO);
        System.out.println("seriesDTO = " + seriesDTO);

        List<PostDTO> postDTO = postService.selectPostList();
        System.out.println("postList = " + postDTO);
        model.addAttribute("postList",postDTO);
        return "/views/index";
    }

    @RequestMapping("/story")
    public String storyPage() {

        return "/views/story/home";
    }

}
