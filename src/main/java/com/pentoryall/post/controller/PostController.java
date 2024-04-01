package com.pentoryall.post.controller;

import com.pentoryall.genre.dto.GenreDTO;
//import com.pentoryall.genre.service.GenreService;
//import com.pentoryall.genreOfArt.dto.GenreOfArtDTO;
//import com.pentoryall.genre.service.GenreService;
//import com.pentoryall.genreOfArt.dto.GenreOfArtDTO;

//import com.pentoryall.genreOfArt.service.GenreOfArtService;

import com.pentoryall.genre.service.GenreService;
import com.pentoryall.genreOfArt.dto.GenreOfArtDTO;
import com.pentoryall.genreOfArt.service.GenreOfArtService;
import com.pentoryall.post.dto.PostDTO;
import com.pentoryall.post.service.PostService;
import com.pentoryall.series.dto.SeriesDTO;
import com.pentoryall.series.service.SeriesService;
import com.pentoryall.user.dto.UserDTO;
import com.pentoryall.user.service.UserService;
import org.springframework.beans.factory.annotation.Value;
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
    @Value("${image.image-dir}")
    private String IMAGE_DIR;
    private final SeriesService seriesService;
    private final PostService postService;
    private final UserService userService;
    private final GenreOfArtService genreOfArtService;
    private final GenreService genreService;

    public PostController(SeriesService seriesService, PostService postService, UserService userService, GenreOfArtService genreOfArtService,GenreService genreService) {
        this.seriesService = seriesService;
        this.postService = postService;
        this.userService = userService;
        this.genreOfArtService = genreOfArtService;
        this.genreService = genreService;
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
                                    @RequestParam List<Long> genreCode,
                                    GenreOfArtDTO genreOfArtDTO,
                                    PostDTO postDTO,
                                    Model model
    ) {
        String title = params.get("title");
        String contents = params.get("contents");
        char isPublic = params.get("isPublic") != null ? params.get("isPublic").charAt(0) : 'n';
        long seriesno = Long.parseLong(params.get("series"));
        char isFee = params.get("isFee") != null ? params.get("isFee").charAt(0) : 'n';
        long neededPoint = Long.parseLong(params.get("neededPoint"));
        char isAdult = params.get("isAdult") != null ? params.get("isAdult").charAt(0) : 'n';


        System.out.println("너 성공한거야!");
        System.out.println(title);
        System.out.println("thumbnailImage = " + thumbnail);
        /*파일 가공 로직*/
        if(!thumbnail.isEmpty() && thumbnail!=null) {
            String filePath = IMAGE_DIR + "post-thumbnail-images";
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

            String saveFileName = "/upload/post-thumbnail-images/" + savedName;


            postDTO.setThumbnailImage(saveFileName);
        }
        SeriesDTO seriesDTO = seriesService.selectSeriesByTitle(seriesno);
        System.out.println(seriesDTO);
        long seriesCode = seriesDTO.getCode();
        postDTO.setTitle(title);
        postDTO.setContent(contents);

        postDTO.setIsPublic(isPublic);
        postDTO.setSeriesCode(seriesCode);
        postDTO.setIsPaid(isFee);
        postDTO.setPrice(neededPoint);
        postDTO.setIsAdult(isAdult);

        System.out.println(postDTO);
        System.out.println("title>>>>>>>>>>>> = " + title);
        postService.insertPost(postDTO);
        System.out.println("삽입 성공!!");

        System.out.println("????????????"+genreOfArtDTO);

        System.out.println("genreCode!!!!!!!!!!!!!!! = " + genreCode);



        for(int i = 0 ; i<genreCode.size();i++){
            long code = genreCode.get(i);
            System.out.println(code);
            GenreDTO genreDTO = genreService.selectGenreTitle(code);
            System.out.println(genreDTO);
            String kind = genreDTO.getName();
            System.out.println(kind);
            genreOfArtDTO.setGenreCode(code);
            genreOfArtDTO.setPostCode(postDTO.getCode());
            genreOfArtDTO.setSeriesCode(seriesDTO.getCode());
            genreOfArtDTO.setKind("POST");
            System.out.println("전");
            System.out.println(genreOfArtDTO);
            genreOfArtService.insertGenreOfArt(genreOfArtDTO);
            System.out.println("후");
        }

        System.out.println("성공하셨스므니다.");




        System.out.println("postDTO = " + postDTO);
//        session.setAttribute("code", postDTO.getCode());

        return "redirect:/post/information?code=" + postDTO.getCode();
    }

    @GetMapping("/information")
    public String getPostInformation(
            long code,
            Model model) {
        PostDTO postDTO = postService.getPostInformationByPostCode(code);
        System.out.println(postDTO);
        System.out.println("code>>>>> = " + code);

        long seriesCode = postDTO.getSeriesCode();
        long userCode = postDTO.getUserCode();

        System.out.println("userCode = " + userCode);
        UserDTO userDTO = userService.getUserInformationByUserCode(userCode);

        SeriesDTO seriesDTO = seriesService.getSeriesInformationBySeriesCode(seriesCode);

        System.out.println("postDTO = " + postDTO);
        System.out.println("userDTO = " + userDTO);
        System.out.println("seriesDTO = " + seriesDTO);
        model.addAttribute("post", postDTO);
        model.addAttribute("user", userDTO);
        model.addAttribute("series", seriesDTO);

        return "views/post/list";
    }

    @GetMapping("/seriesList")
    public @ResponseBody List<SeriesDTO> functionGetSeriesList() {
        List<SeriesDTO> seriesList = seriesService.getSeriesList(1);
        System.out.println(seriesList);
        return seriesList;
    }

}