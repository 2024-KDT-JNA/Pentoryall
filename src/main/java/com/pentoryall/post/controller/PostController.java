package com.pentoryall.post.controller;

import com.pentoryall.post.dto.PostDTO;
import com.pentoryall.post.service.PostService;
import com.pentoryall.series.dto.SeriesDTO;
import com.pentoryall.series.service.SeriesService;
import com.pentoryall.user.dto.UserDTO;
import com.pentoryall.user.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    private final MessageSourceAccessor messageSourceAccessor;

    public PostController(SeriesService seriesService, PostService postService, UserService userService, GenreOfArtService genreOfArtService, GenreService genreService, MessageSourceAccessor messageSourceAccessor) {
        this.seriesService = seriesService;
        this.postService = postService;
        this.userService = userService;
        this.genreOfArtService = genreOfArtService;
        this.genreService = genreService;
        this.messageSourceAccessor = messageSourceAccessor;
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
        char isPublic = params.get("isPublic") != null ? params.get("isPublic").charAt(0) : 'N';
        long seriesno = Long.parseLong(params.get("series"));
        char isFee = params.get("isFee") != null ? params.get("isFee").charAt(0) : 'N';
        long neededPoint = Long.parseLong(params.get("neededPoint"));
        char isAdult = params.get("isAdult") != null ? params.get("isAdult").charAt(0) : 'N';


        System.out.println("너 성공한거야!");
        System.out.println(title);
        System.out.println("thumbnailImage = " + thumbnail);
        /*파일 가공 로직*/
        if (!thumbnail.isEmpty() && thumbnail != null) {
            String filePath = IMAGE_DIR + "post-thumbnail-images";
            String originFileName = thumbnail.getOriginalFilename();// 업로드 파일명
            String ext = originFileName.substring(originFileName.lastIndexOf("."));// 업로드 파일명에서 확장자 분리
            String savedName = UUID.randomUUID() + ext;// 고유한 파일명 생성 + 확장자 추가

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

        System.out.println("????????????" + genreOfArtDTO);

        System.out.println("genreCode!!!!!!!!!!!!!!! = " + genreCode);


        for (int i = 0; i < genreCode.size(); i++) {
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

        String url = "redirect:/series/page?code=" + seriesCode;
        return url;
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

    @GetMapping("/updatewriter")
    public String updatePostWriter(long code,
                                   HttpSession session,
                                   Model model
    ) {
        PostDTO postDTO = postService.getPostInformationByPostCode(code);
        System.out.println("postDTO$$$$$$$$$$ = " + postDTO);
        //        List<GenreOfArtDTO> genreList = genreOfArtService.findGenreBySeriesCode(postDTO.getSeriesCode());
        //        System.out.println("genreList ^^^^^^^^^^^= " + genreList);
        model.addAttribute("post", postDTO);
        //        model.addAttribute("genreList",genreList);
        return "/views/post/updatewriter";
    }

    @GetMapping("/update")
    public String updatePostOptions(long code,
                                    Model model) {
        System.out.println("code !!!!!!!= " + code);
        System.out.println("성공해써");

        PostDTO postDTO = postService.getPostInformationByPostCode(code);
        List<GenreOfArtDTO> genreOfArtDTO = genreOfArtService.selectGenreByPostCode(code);

        System.out.println("장르코코 : " + genreOfArtDTO);
        System.out.println("포스트코코 : " + postDTO);

        model.addAttribute("post", postDTO);
        model.addAttribute("genre", genreOfArtDTO);
        return "/views/post/update";
    }

    @PostMapping("/update")
    public String afterUpdatePost(
            @ModelAttribute PostDTO postDTO,
            @RequestParam Map<String, String> params,
            @RequestParam(required = false) MultipartFile thumbnail,
            @RequestParam List<Long> genreCode,
            Model model
    ) {
        char isPublic = params.get("isOpen") != null ? params.get("isOpen").charAt(0) : 'N';
        char isPaid = params.get("isFee") != null ? params.get("isFee").charAt(0) : 'N';
        char isAdult = params.get("isPossible") != null ? params.get("isPossible").charAt(0) : 'N';

        postDTO.setIsPublic(isPublic);
        postDTO.setIsPaid(isPaid);
        postDTO.setIsAdult(isAdult);

        /*파일 가공 로직*/
        if (!thumbnail.isEmpty() && thumbnail != null) {
            String filePath = IMAGE_DIR + "post-thumbnail-images";
            String originFileName = thumbnail.getOriginalFilename();// 업로드 파일명
            String ext = originFileName.substring(originFileName.lastIndexOf("."));// 업로드 파일명에서 확장자 분리
            String savedName = UUID.randomUUID() + ext;// 고유한 파일명 생성 + 확장자 추가

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

        System.out.println("postDTO ~~~~~~~~~~~~~= " + postDTO);
        System.out.println("genreCode$$$$$$$$$$$ = " + genreCode);

        /*장르 수정*/
        GenreOfArtDTO genreOfArtDTO = new GenreOfArtDTO();
        genreOfArtDTO.setPostCode(postDTO.getCode());
        genreOfArtDTO.setSeriesCode(postDTO.getSeriesCode());

        genreOfArtService.deleteSeriesGenreByPostCode(postDTO.getCode());
        System.out.println("삭제 성공!!!");
        for (int i = 0; i < genreCode.size(); i++) {
            genreOfArtDTO.setGenreCode(genreCode.get(i));
            genreOfArtService.insertGenreForDTO(genreOfArtDTO);
        }
        System.out.println("수정 성공!!!");
        System.out.println("postDTO = " + postDTO);
        /*포스트 정보 수정*/
        postService.updatePostService(postDTO);

        System.out.println("포스트 정보 수정 완료!");

        long seriesCode = postDTO.getSeriesCode();

        String url = "redirect:/series/page?code=" + seriesCode;
        return url;
    }

    @GetMapping("/delete")
    public String deletePost(@RequestParam long code,
                             RedirectAttributes rttr) {
        rttr.addFlashAttribute("message", messageSourceAccessor.getMessage("post.delete"));
        PostDTO postDTO = postService.getPostInformationByPostCode(code);

        long seriesCode = postDTO.getSeriesCode();

        genreOfArtService.deleteSeriesGenreByPostCode(code);
        System.out.println("장르에 포함된 포스트가 삭제 되었습니다.");

        postService.deletePostByPostCode(code);
        System.out.println("포스트가 삭제 되었습니다.");
        String url = "redirect:/series/page?code=" + seriesCode;
        return url;
    }
}