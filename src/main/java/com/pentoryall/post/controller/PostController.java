package com.pentoryall.post.controller;

import com.pentoryall.post.dto.PostDTO;
import com.pentoryall.post.service.PostService;
import com.pentoryall.series.dto.SeriesDTO;
import com.pentoryall.series.service.SeriesService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

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
    public void writeController() {
    }

    @GetMapping("/list")
    public void listController() {
    }

    @GetMapping("/add")
    public void postAddPageController() {
    }

    @PostMapping("/add")
    public String postAddController(@RequestParam Map<String, String> params,
                                    @RequestParam(required = false) MultipartFile thumbnail,
                                    PostDTO postDTO,
                                    HttpSession session,
                                    Model model
    ) {
        String title = params.get("title");
        String contents = params.get("contents");
        char isPublic = params.get("isPublic").charAt(0);
        String series = params.get("series");
        char isFee = params.get("isFee").charAt(0);
        long neededPoint = Long.parseLong(params.get("neededPoint"));
        char isAdult = params.get("isAdult").charAt(0);

        System.out.println(title);
        System.out.println("thumbnailImage = " + thumbnail);
        /*파일 가공 로직*/
        String root = "C:/00_Pactoryall";
        String filePath = root + "/post-thumbnail-images";
        String originFileName = thumbnail.getOriginalFilename();//업로드 파일명
        String ext = originFileName.substring(originFileName.lastIndexOf("."));//업로드 파일명에서 확장자 분리
        String savedName = UUID.randomUUID() + ext;//고유한 파일명 생성 + 확장자 추가

        String finalFilePath = filePath + "/" + savedName;
        File dir = new File(filePath);
        if (!dir.exists()) dir.mkdirs();

        try {
            thumbnail.transferTo(new File(finalFilePath));
            model.addAttribute("savedName", savedName);
        } catch (IOException e) {
            e.printStackTrace();
        }

        SeriesDTO seriesDTO = seriesService.selectSeriesByTitle(series);
        System.out.println(seriesDTO);
        long seriesCode = seriesDTO.getCode();

        postDTO.setTitle(title);
        postDTO.setContent(contents);
        postDTO.setThumbnailImage(finalFilePath);
        postDTO.setIsPublic(isPublic);
        postDTO.setSeriesCode(seriesCode);
        postDTO.setIsPaid(isFee);
        postDTO.setPrice(neededPoint);
        postDTO.setIsAdult(isAdult);


        postService.insertPost(postDTO);

        session.setAttribute("code", postDTO.getCode());

        return "redirect:/post/information";
    }

    @GetMapping("/information")
    public String getPostInformation(HttpSession session,
                                     Model model) {

        long code = (long) session.getAttribute("code");

        long lastCode = code+1;

        PostDTO postDTO = postService.getPostInformation(lastCode);
        System.out.println(postDTO);
        model.addAttribute("post",postDTO);
        return "/post/list";
    }
}
