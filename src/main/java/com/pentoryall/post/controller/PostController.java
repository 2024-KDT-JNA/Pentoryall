package com.pentoryall.post.controller;

import com.pentoryall.post.dto.PostDTO;
import com.pentoryall.post.service.PostService;
import com.pentoryall.series.dto.SeriesDTO;
import com.pentoryall.series.service.SeriesService;
import com.pentoryall.user.dto.UserDTO;
import com.pentoryall.user.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;


@Controller
@RequestMapping("/post")
public class PostController {

    private final SeriesService seriesService;
    private final PostService postService;
    private final UserService userService;

    public PostController(SeriesService seriesService, PostService postService, UserService userService) {
        this.seriesService = seriesService;
        this.postService = postService;
        this.userService = userService;

    }


    @GetMapping("/writer")
    public String writeController() {
        return "views/post/writer";
    }

    @GetMapping("/list")
    public String listController() {
        return "views/post/list";
    }

    @GetMapping("/add")
    public String postAddPageController() {
        return "views/post/add";
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
        char isPublic = params.get("isPublic") != null ? params.get("isPublic").charAt(0) : 'n';
        String series = params.get("series");
        char isFee = params.get("isFee") != null ? params.get("isFee").charAt(0) : 'n';
        long neededPoint = Long.parseLong(params.get("neededPoint"));
        char isAdult = params.get("isAdult") != null ? params.get("isAdult").charAt(0) : 'n';

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

        System.out.println(postDTO);
        postService.insertPost(postDTO);

        session.setAttribute("code", postDTO.getCode());

        return "redirect:/post/information?code="+postDTO.getCode();
    }

    @GetMapping("/information")
    public String getPostInformation(HttpSession session,
                                     Model model) {

        System.out.println("리다이렉트 성공!");

        long code = (long) session.getAttribute("code");

        System.out.println(code);
        PostDTO postDTO = postService.getPostInformationByPostCode(code);
        System.out.println(postDTO);

        long seriesCode = postDTO.getSeriesCode();

        long userCode = postDTO.getUserCode();

        System.out.println("userCode = " + userCode);
        UserDTO userDTO = userService.getUserInformationByUserCode(userCode);

        SeriesDTO seriesDTO = seriesService.getSeriesInformationBySeriesCode(seriesCode);

        System.out.println("postDTO = " + postDTO);
        System.out.println("userDTO = " + userDTO);
        System.out.println("seriesDTO = " + seriesDTO);
        model.addAttribute("post",postDTO);
        model.addAttribute("user",userDTO);
        model.addAttribute("series",seriesDTO);

        return "views/post/list";
    }

    @GetMapping("/seriesList")
    public @ResponseBody List<SeriesDTO> functionGetSeriesList(){
        List<SeriesDTO> seriesList = seriesService.getSeriesList(1);
        System.out.println(seriesList);
        return seriesList;

    }

}