package com.pentoryall.series.controller;

import com.pentoryall.genre.dto.GenreDTO;
import com.pentoryall.genre.service.GenreService;
import com.pentoryall.genreOfArt.dto.GenreOfArtDTO;
import com.pentoryall.genreOfArt.service.GenreOfArtService;
import com.pentoryall.post.dto.PostDTO;
import com.pentoryall.post.service.PostService;
import com.pentoryall.series.dto.SeriesDTO;
import com.pentoryall.series.service.SeriesService;
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
import java.util.*;

@Controller
@RequestMapping("/series")
public class SeriesController {

    @Value("${image.image-dir}")
    private String IMAGE_DIR;
    private final SeriesService seriesService;
    private final GenreOfArtService genreOfArtService;
    private final GenreService genreService;
    private final PostService postService;
    private final MessageSourceAccessor messageSourceAccessor;

    public SeriesController(SeriesService seriesService, GenreOfArtService genreOfArtService, GenreService genreService, PostService postService, MessageSourceAccessor messageSourceAccessor) {
        this.seriesService = seriesService;
        this.genreOfArtService = genreOfArtService;
        this.genreService = genreService;
        this.postService = postService;
        this.messageSourceAccessor = messageSourceAccessor;
    }

    @GetMapping("/add")
    public String addSeries() {
        return "/views/series/add";
    }

    @GetMapping("/page")
    public String seriesPage(long code,
                             PostDTO postDTO,
                             Model model) {
        SeriesDTO seriesDTO = seriesService.findSeriesByCode(code);
        model.addAttribute("series", seriesDTO);
        List<GenreOfArtDTO> genreOfArtDTO = genreOfArtService.findGenreBySeriesCode(code);
        List<String> genreNames = new ArrayList<>();
        for (int i = 0; i < genreOfArtDTO.size(); i++) {
            GenreDTO genreDTO = genreService.selectGenreTitle(genreOfArtDTO.get(i).getGenreCode());
            genreNames.add(genreDTO.getName());
        }

        List<PostDTO> postLists = postService.selectPostsBySeriesCode(code);


        System.out.println("genreNames = " + genreNames);
        model.addAttribute("postList", postLists);
        System.out.println("postLists **********= " + postLists);
        model.addAttribute("genreNames", genreNames);
        return "/views/series/page";
    }

    @PostMapping("/add")
    public String addSeriesOptions(
            @RequestParam(required = false) MultipartFile thumbnail,
            @ModelAttribute("series") SeriesDTO seriesDTO,
            @RequestParam List<Long> genreCode,
            GenreOfArtDTO genreOfArtDTO,
            Model model
    ) {

        System.out.println("seriesDTO = " + seriesDTO);

        seriesDTO.setUserCode(1);

        System.out.println("seriesDTO = " + seriesDTO);
        System.out.println("thumbnailImage = " + thumbnail);
        String savePath = "";
        /*파일 가공*/
        if (thumbnail != null && !thumbnail.isEmpty()) {
            String filePath = IMAGE_DIR + "series-thumbnail-images";

            File dir = new File(filePath);

            if (!dir.exists()) dir.mkdirs();

            String originFileName = thumbnail.getOriginalFilename();// 업로드 파일명
            String ext = originFileName.substring(originFileName.lastIndexOf("."));// 업로드 파일명에서 확장자 분리
            String savedName = UUID.randomUUID() + ext;// 고유한 파일명 생성 + 확장자 추가
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

        for (int i = 0; i < genreCode.size(); i++) {
            long code = genreCode.get(i);
            genreOfArtDTO.setGenreCode(code);
            genreOfArtDTO.setSeriesCode(seriesCode);
            genreOfArtDTO.setKind("SERIES");
            genreOfArtService.insertSeriesGenre(genreOfArtDTO);
        }

        System.out.println("성공함");
        long urlCode = seriesDTO.getCode() + 1;
        return "redirect:/series/page?code=" + urlCode;
    }

    @GetMapping("/update")
    public String updateSeries(@RequestParam long code,
                               Model model,
                               HttpSession session) {
        SeriesDTO seriesDTO = seriesService.getSeriesInformationBySeriesCode(code);
        System.out.println("seriesDTO = " + seriesDTO);

        List<String> genreNames = new ArrayList<>();
        List<GenreOfArtDTO> genreList = genreOfArtService.findGenreBySeriesCode(code);
        for (int i = 0; i < genreList.size(); i++) {
            GenreDTO genreDTO = genreService.selectGenreTitle(genreList.get(i).getGenreCode());
            genreNames.add(genreDTO.getName());
        }

        session.setAttribute("code", code);
        model.addAttribute("series", seriesDTO);
        model.addAttribute("genreNames", genreNames);
        return "/views/series/update";
    }

    @PostMapping("/update")
    public String updateSeriesInformation(@ModelAttribute SeriesDTO seriesDTO,
                                          @RequestParam List<Long> genreCode,
                                          @RequestParam(required = false) MultipartFile thumbnail,
                                          GenreOfArtDTO genreOfArtDTO,
                                          HttpSession session,
                                          RedirectAttributes rttr) {

        rttr.addFlashAttribute("message", "series.update");

        long code = (long) session.getAttribute("code");

        System.out.println("genreCode = " + genreCode);


        /*파일 가공*/
        String savePath = "";
        if (thumbnail != null && !thumbnail.isEmpty()) {

            String filePath = IMAGE_DIR + "series-thumbnail-images";

            File dir = new File(filePath);

            if (!dir.exists()) dir.mkdirs();


            String originFileName = thumbnail.getOriginalFilename();// 업로드 파일명
            String ext = originFileName.substring(originFileName.lastIndexOf("."));// 업로드 파일명에서 확장자 분리
            String savedName = UUID.randomUUID() + ext;// 고유한 파일명 생성 + 확장자 추가
            System.out.println("filePath = " + filePath);
            String finalFilePath = filePath + "/" + savedName;
            System.out.println("finalFilePath = " + finalFilePath);

            try {
                thumbnail.transferTo(new File(finalFilePath));
            } catch (IOException e) {
                e.printStackTrace();
            }

            savePath = "/upload/series-thumbnail-images" + "/" + savedName;
        }
        Set postNo = new HashSet<>();
        List<GenreOfArtDTO> genreOfArtDTOList = genreOfArtService.selectPostInSeries(code);
        for (int i = 0; i < genreOfArtDTOList.size(); i++) {
            postNo.add(genreOfArtDTOList.get(i).getPostCode());
        }

        System.out.println("포오스틱:" + postNo);

        List<Long> postList = List.copyOf(postNo);

        System.out.println("포으스틱 배열로 변환 = " + postList);
        /*Genre 코드 genreOfArtDTO에 삽입*/
        for (int i = 0; i < genreCode.size(); i++) {
            genreOfArtService.deleteSeriesGenreBySeriesCode(code);
            System.out.println("삭삭제 완료!");
        }
        for (int i = 0; i < genreCode.size(); i++) {
            genreOfArtService.insertGenreBySeriesCode(code, genreCode.get(i));
            for (int k = 0; k < postList.size(); k++) {
                genreOfArtService.insertGenreBySeriesCodePost(postList.get(k), code, genreCode.get(i));
            }
            System.out.println("수수정 완료!");
        }

        seriesDTO.setThumbnailImage(savePath);
        System.out.println("seriesDTO = " + seriesDTO);

        System.out.println("code ??????= " + code);
        seriesService.updateSeries(seriesDTO, code);
        System.out.println("성공하셨습니다앙");

        //        genreOfArtService.updateGenreOfArt(genreOfArtDTO);
        String url = "redirect:/series/page?code=" + code;
        return url;
    }

    @GetMapping("/delete")
    public String deleteSeries(@RequestParam long code) {
        genreOfArtService.deleteSeriesGenreBySeriesCode(code);
        System.out.println("성공1");
        postService.deleteSeriesBySeriesCode(code);
        System.out.println("성공2");
        seriesService.deleteSeries(code);
        System.out.println("성공3");
        return "/views/index";
    }
}