package com.pentoryall.post.controller;

import com.pentoryall.comment.dto.CommentDetailDTO;
import com.pentoryall.comment.service.CommentService;
import com.pentoryall.genre.dto.GenreDTO;
import com.pentoryall.genre.service.GenreService;
import com.pentoryall.genreOfArt.dto.GenreOfArtDTO;
import com.pentoryall.genreOfArt.service.GenreOfArtService;
import com.pentoryall.post.dto.PostDTO;
import com.pentoryall.post.dto.ReportPostDTO;
import com.pentoryall.post.service.PostService;
import com.pentoryall.post.service.ReportPostService;
import com.pentoryall.series.dto.SeriesDTO;
import com.pentoryall.series.service.SeriesService;
import com.pentoryall.user.dto.LikeDTO;
import com.pentoryall.user.dto.LikePostDTO;
import com.pentoryall.user.dto.UserDTO;
import com.pentoryall.user.service.LikePostService;
import com.pentoryall.user.service.LikeService;
import com.pentoryall.user.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.util.*;


@Controller
@RequestMapping("/post")
public class PostController {

    @Value("${image.image-dir}")
    private String IMAGE_DIR;
    private final SeriesService seriesService;
    private final PostService postService;
    private final UserService userService;
    private final CommentService commentService;
    private final GenreOfArtService genreOfArtService;
    private final GenreService genreService;
    private final MessageSourceAccessor messageSourceAccessor;
    private final LikeService likeService;
    private final LikePostService likePostService;
    private final ReportPostService reportPostService;

    public PostController(SeriesService seriesService, PostService postService, UserService userService, CommentService commentService, GenreOfArtService genreOfArtService, GenreService genreService, MessageSourceAccessor messageSourceAccessor, LikeService likeService, LikePostService likePostService, ReportPostService reportPostService) {
        this.seriesService = seriesService;
        this.postService = postService;
        this.userService = userService;
        this.commentService = commentService;
        this.genreOfArtService = genreOfArtService;
        this.genreService = genreService;
        this.messageSourceAccessor = messageSourceAccessor;
        this.likeService = likeService;
        this.likePostService = likePostService;
        this.reportPostService = reportPostService;
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
                                    @AuthenticationPrincipal UserDTO userDTO,
                                    PostDTO postDTO,
                                    Model model
    ) {
        String title = params.get("title");
        String contents = params.get("contents");
        char isPublic = params.get("isPublic") != null ? params.get("isPublic").charAt(0) : 'N';
        long seriesno = Long.parseLong(params.get("series"));
        char isFee = params.get("isFee") != null ? params.get("isFee").charAt(0) : 'N';
        int neededPoint = Integer.parseInt(params.get("neededPoint"));
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
        postDTO.setUserCode(userDTO.getCode());
        postService.insertPost(postDTO);
        System.out.println("삽입 성공!!");

        System.out.println("????????????" + genreOfArtDTO);

        System.out.println("genreCode!!!!!!!!!!!!!!! = " + genreCode);

        PostDTO postDTO1 = postService.getLatestPost();

        System.out.println("postDTO1 = " + postDTO1);

        for (int i = 0; i < genreCode.size(); i++) {
            long code = genreCode.get(i);
            System.out.println(code);
            GenreDTO genreDTO = genreService.selectGenreTitle(code);
            System.out.println(genreDTO);
            String kind = genreDTO.getName();
            System.out.println(kind);
            genreOfArtDTO.setGenreCode(code);
            genreOfArtDTO.setPostCode(postDTO1.getCode());
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
            Model model,
            @AuthenticationPrincipal UserDTO user) {
        postService.updateViews(code);
        PostDTO postDTO = postService.getPostInformationByPostCode(code);
        System.out.println(postDTO);
        System.out.println("code>>>>> = " + code);

        long seriesCode = postDTO.getSeriesCode();
        long userCode = postDTO.getUserCode();

        System.out.println("userCode = " + userCode);
        UserDTO userDTO = userService.getUserInformationByUserCode(userCode);

        SeriesDTO seriesDTO = seriesService.getSeriesInformationBySeriesCode(seriesCode);

        List<PostDTO> postList = postService.selectPostListBySeriesCode(seriesCode);

        model.addAttribute("postList",postList);
        List<CommentDetailDTO> commentList = commentService.selectCommentByPostCode(code);
        List<CommentDetailDTO> replyList = commentService.selectRefCommentByPostCode(code);
        System.out.println("commentList =!! " + commentList);
        System.out.println("답글 리스트 = " + replyList);

        LikeDTO likeDTO = likeService.selectLikeByUserAndPost(user.getCode(), code);
        System.out.println("user.getCode() = " + user.getCode());
        model.addAttribute("loginUser", user.getCode());
        model.addAttribute("like", likeDTO);
        if (likeDTO != null) {
            model.addAttribute("isLiked", true);
        } else {
            model.addAttribute("isLiked", false);
        }
        int likeCount = 0;
        List<LikeDTO> likeList = likeService.selectLikeByPostCode(code);
        if (likeList != null && !likeList.isEmpty()) {
            likeCount = likeList.size();
        }
        model.addAttribute("likeCount", likeCount);

        if (!commentList.isEmpty() || commentList != null) {
            //            System.out.println("유저 정보 : " + commentList.get(0).getUser());
            System.out.println("commentList =>>> " + commentList);
            System.out.println("postDTO = " + postDTO);
            //            System.out.println("userDTO = " + userDTO);
        }
        System.out.println("seriesDTO = " + seriesDTO);
        model.addAttribute("userDTO", userDTO);
        model.addAttribute("post", postDTO);
        //            model.addAttribute("user", userDTO);
        model.addAttribute("series", seriesDTO);
        model.addAttribute("commentList", commentList);
        if (user != null) {
            model.addAttribute("userCode", user.getCode());
        }
        model.addAttribute("replyList", replyList);
        System.out.println("여기까지왓니");


        return "views/post/list";
    }

    @GetMapping("/seriesList")
    public @ResponseBody List<SeriesDTO> functionGetSeriesList(@AuthenticationPrincipal UserDTO user) {
        System.out.println("user = " + user);
        List<SeriesDTO> seriesList = seriesService.getSeriesList(user.getCode());
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
        System.out.println("성공");
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
        rttr.addFlashAttribute("alertMessage", messageSourceAccessor.getMessage("post.delete"));
        PostDTO postDTO = postService.getPostInformationByPostCode(code);

        long seriesCode = postDTO.getSeriesCode();

        genreOfArtService.deleteSeriesGenreByPostCode(code);
        System.out.println("장르에 포함된 포스트가 삭제 되었습니다.");

        likeService.deleteLikeByPostCode(code);
        System.out.println("좋아요가 삭제 되었습니다.");

        postService.deletePostByPostCode(code);
        System.out.println("포스트가 삭제 되었습니다.");
        String url = "redirect:/series/page?code=" + seriesCode;
        return url;
    }

    @PostMapping("/addComment")
    public ResponseEntity<String> addComment(@RequestBody CommentDetailDTO commentAdd,
                                             @AuthenticationPrincipal UserDTO user) {
        commentAdd.setCode(1L);
        System.out.println("commentAdd1 :" + commentAdd.getCode());
        System.out.println("commentAdd1.5 :" + commentAdd.getPostCode());
        System.out.println("commentAdd2 :" + commentAdd.getContent());
        System.out.println("도달하고 있는가");
        commentAdd.setUser(user);
        System.out.println("commentAdd = " + commentAdd);
        commentService.addComment(commentAdd);
        System.out.println("db에 잘 등록 됬어요~");
        return ResponseEntity.ok("댓글 등록 완료");
    }

    @GetMapping("/loadComment")
    public ResponseEntity<List<CommentDetailDTO>> loadComment(CommentDetailDTO commentDTO) {
        System.out.println("commentDTO = " + commentDTO);
        List<CommentDetailDTO> commentList = commentService.loadComment(commentDTO);
        System.out.println("commentList^^ = " + commentList);
        return ResponseEntity.ok(commentList);
    }

    //    @GetMapping("/loadReply")
    //    public ResponseEntity<List<CommentDetailDTO>> loadReply(CommentDetailDTO commentDTO){
    //        List<CommentDetailDTO> commentList = commentService.loadReply(commentDTO);
    //        return ResponseEntity.ok(commentList);
    //    }
    @PostMapping("/removeComment")
    public ResponseEntity<String> removeReply(@RequestBody CommentDetailDTO commentDetailDTO) {
        System.out.println("commentDetailDTO =~~~~~~ " + commentDetailDTO.getCode());
        commentService.removeReply(commentDetailDTO);
        System.out.println("잘 삭제되어씀");
        return ResponseEntity.ok("댓글 삭제 완료");
    }

    @PostMapping("/updateComment")
    public ResponseEntity<String> updateComment(@RequestBody CommentDetailDTO commentDetailDTO) {
        System.out.println("commentDetailDTO = " + commentDetailDTO);
        System.out.println("hi");
        commentService.updateComment(commentDetailDTO);
        System.out.println("잘 수정되어씀");
        return ResponseEntity.ok("댓글 수정 완료");
    }

    @PostMapping("/genre")
    public String selectByGenre(@RequestParam List<Long> genre,
                                Model model) {

        System.out.println("genre = " + genre);

        List<GenreOfArtDTO> genreOfArtDTOList = genreOfArtService.selectSeriesByGenre(genre.get(0));
        List<Long> seriesCode = new ArrayList<>();
        for (int i = 0; i < genreOfArtDTOList.size(); i++) {
            seriesCode.add(genreOfArtDTOList.get(i).getSeriesCode());
        }
        System.out.println("seriesCode = " + seriesCode);
        System.out.println("genreOfArtDTOList = " + genreOfArtDTOList);

        boolean[] result = new boolean[seriesCode.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = true;
        }
        System.out.println("result[0] = " + result[0]);
        for (int i = 0; i < seriesCode.size(); i++) {
            for (int k = 1; k < genre.size(); k++) {
                GenreOfArtDTO genreOfArtDTO = genreOfArtService.selectSeriesGenre(seriesCode.get(i), genre.get(k));
                if (genreOfArtDTO == null) {
                    result[i] = false;
                }
            }
        }

        System.out.println("result =>> " + Arrays.toString(result));

        List<Integer> temp = new ArrayList<>();
        for (int i = 0; i < result.length; i++) {
            if (result[i] == true) {
                temp.add(i);
            }
        }
        List<SeriesDTO> seriesList = new ArrayList<>();
        for (int i = 0; i < temp.size(); i++) {
            SeriesDTO seriesDTO = seriesService.selectSeriesByTitle(seriesCode.get((temp.get(i))));
            seriesList.add(seriesDTO);
        }
        System.out.println("seriesList =>> " + seriesList);

        /*포스트 검색*/
        List<PostDTO> postList = new ArrayList<>();
        List<PostDTO> postDTO = new ArrayList<>();
        for (int i = 0; i < seriesList.size(); i++) {
            postDTO = postService.selectPostsBySeriesCode(seriesList.get(i).getCode());
            for (int k = 0; k < postDTO.size(); k++) {
                postList.add(postDTO.get(k));
            }
        }
        //
        //        List<Long> postNo = new ArrayList<>();
        //        for(int i =0 ; i<genre.size();i++) {
        //            List<GenreOfArtDTO> genreOfArtDTO = genreOfArtService.selectPostNotInSeries(genre.get(i));
        //            for(int k = 0 ; k<genreOfArtDTO.size();k++) {
        //                postNo.add(genreOfArtDTO.get(k).getPostCode());
        //            }
        //        }
        //
        //        System.out.println("postNo = " + postNo);
        //        for(int i = 0 ; i<postNo.size() ; i++){
        //            PostDTO postDTO1 = postService.getPostInformationByPostCode(postNo.get(i));
        //            postList.add(postDTO1);
        //        }
        System.out.println(" 포스트리스트= " + postList);

        model.addAttribute("seriesList", seriesList);
        model.addAttribute("postList", postList);
        return "/views/series/select";

    }

    //
    @PostMapping("/addReply")
    public ResponseEntity<String> addReply(@RequestBody CommentDetailDTO commentAdd,
                                           @AuthenticationPrincipal UserDTO user) {
        commentAdd.setCode(1L);
        System.out.println("도달하고 있는가");
        commentAdd.setUser(user);
        System.out.println("commentAdd = " + commentAdd);
        commentService.addRefComment(commentAdd);
        System.out.println("마라탕 먹고싶어요~");
        return ResponseEntity.ok("댓글 등록 완료");
    }

    @GetMapping("/loadReply")
    public ResponseEntity<List<CommentDetailDTO>> loadReply(CommentDetailDTO commentDTO) {
        System.out.println("commentDTO = " + commentDTO);
        List<CommentDetailDTO> commentList = commentService.loadReply(commentDTO);
        System.out.println("가져와진 답글들 ^^ = " + commentList);
        return ResponseEntity.ok(commentList);
    }

    @GetMapping("/additionalData")
    public ResponseEntity<List<CommentDetailDTO>> loadAdditionalData(CommentDetailDTO commentDTO) {
        System.out.println("commentDTO = " + commentDTO);
        List<CommentDetailDTO> commentList = commentService.loadAdditionalData(commentDTO);
        System.out.println("가져와진 답글들 ^^ = " + commentList);
        return ResponseEntity.ok(commentList);
    }

    @PostMapping("/likeCount")
    public ResponseEntity<Integer> selectLikeCount(@RequestBody PostDTO postDTO) {
        System.out.println("postDTO = " + postDTO);
        Integer result;
        List<LikeDTO> likeList = likeService.selectLikeByPostCode(postDTO.getCode());
        if (likeList != null && !likeList.isEmpty()) {
            result = likeList.size();
        } else {
            result = 0;
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("/likeTitle")
    public ResponseEntity<Integer> selectLike(@RequestBody PostDTO postDTO) {
        System.out.println("postDTO = " + postDTO);
        Integer result = 0;
        List<LikePostDTO> likeList = likePostService.selectLikeByPostTitle(postDTO.getTitle());
        System.out.println("likeList = " + likeList);
        if (likeList != null && !likeList.isEmpty()) {
            result = likeList.size();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/postReport")
    public String reportPostPage(@RequestParam Long code,
                                 @RequestParam Long postCode,
                                 @RequestParam Long userCode,
                                 HttpSession session){
        System.out.println("code = " + code);
        System.out.println("postCode = " + postCode);
        System.out.println("userCode = " + userCode);
        session.setAttribute("code",code);
        session.setAttribute("postCode",postCode);
        session.setAttribute("userCode",userCode);

        return "views/post/postReport";
    }
    @PostMapping("/postReport")
    public String reportPost(@ModelAttribute ReportPostDTO reportPostDTO,
                             HttpSession session){

        reportPostDTO.setPostCode((Long) session.getAttribute("postCode"));
        reportPostDTO.setUserCode((Long)session.getAttribute("userCode"));
        System.out.println("reportPostDTO = " + reportPostDTO);
        reportPostService.insertReportPost(reportPostDTO);
        System.out.println("ㅇㅈ");
        return "views/index";
    }

    @GetMapping("/postComment")
    public String reportCommentPage(@RequestParam Long code,
                                 @RequestParam Long postCode,
                                 @RequestParam Long userCode,
                                 @RequestParam Long commentCode,
                                 HttpSession session){
        System.out.println("code = " + code);
        System.out.println("postCode = " + postCode);
        System.out.println("userCode = " + userCode);
        System.out.println("commentCode = " + commentCode);
        session.setAttribute("code",code);
        session.setAttribute("postCode",postCode);
        session.setAttribute("userCode",userCode);
        session.setAttribute("commentCode",commentCode);
        return "views/post/postComment";
    }
    @PostMapping("/postComment")
    public String reportComment(@ModelAttribute ReportPostDTO reportPostDTO,
                             HttpSession session){

        reportPostDTO.setPostCode((Long) session.getAttribute("postCode"));
        reportPostDTO.setUserCode((Long)session.getAttribute("userCode"));
        reportPostDTO.setCommentCode((Long) session.getAttribute("commentCode"));
        System.out.println("reportPostDTO = " + reportPostDTO);
        reportPostService.insertCommentPost(reportPostDTO);
        System.out.println("인ㅈ");
        return "views/index";
    }
}