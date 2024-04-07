package com.pentoryall.common.controller;

import com.pentoryall.genre.dto.GenreDTO;
import com.pentoryall.genre.service.GenreService;
import com.pentoryall.post.dto.PostDTO;
import com.pentoryall.post.service.PostService;
import com.pentoryall.series.dto.SeriesDTO;
import com.pentoryall.series.service.SeriesService;
import com.pentoryall.user.dto.LikePostDTO;
import com.pentoryall.user.dto.UserDTO;
import com.pentoryall.user.service.LikePostService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class MainController {

    private final SeriesService seriesService;
    private final PostService postService;
    private final GenreService genreService;
    private final LikePostService likePostService;

    public MainController(SeriesService seriesService, PostService postService, GenreService genreService, LikePostService likePostService) {
        this.seriesService = seriesService;
        this.postService = postService;
        this.genreService = genreService;
        this.likePostService = likePostService;
    }

    @RequestMapping({ "/", "/main", "/index" })
    public String defaultLocation(Model model,
                                  @AuthenticationPrincipal UserDTO userDTO) {
        List<SeriesDTO> seriesDTO = seriesService.selectSeriesList();
        model.addAttribute("seriesList",seriesDTO);
        System.out.println("seriesDTO = " + seriesDTO);



        List<GenreDTO> genreList = genreService.getGenreList();
        

        List<Long> postCodeList = likePostService.selectTop5Post();
        System.out.println("상위 5개 = " + postCodeList);

        List<PostDTO> postDTO = new ArrayList<>();
        
        for(int i = 0 ; i<postCodeList.size();i++){
            PostDTO postDTO1 = postService.getPostInformationByPostCode(postCodeList.get(i));
            postDTO.add(postDTO1);
        }
        System.out.println("postDTO = " + postDTO);
        
        if(userDTO!=null){
            System.out.println("userDTO = " + userDTO);
        }
        model.addAttribute("postList",postDTO);
        model.addAttribute("genreList",genreList);
        return "/views/index";
    }

    @RequestMapping("/story")
    public String storyPage() {

        return "/views/story/home";
    }

}
