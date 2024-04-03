package com.pentoryall.user.controller;

import com.pentoryall.user.dto.LikeDTO;
import com.pentoryall.user.dto.LikePostDTO;
import com.pentoryall.user.dto.UserDTO;
import com.pentoryall.user.service.LikePostService;
import com.pentoryall.user.service.LikeService;
import com.pentoryall.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class LikeController {

    private final LikeService likeService;
    private final LikePostService likePostService;
    private final UserService userService;

    /* 내가 한 좋아요 목록 조회 start */
    @GetMapping("/like")
    public String getLikedPostsByUserCode(@AuthenticationPrincipal UserDTO user, Model model) {
        log.info("user:{}", user);
        /* 좋아요 한 포스트의 총 개수 */
        int likeCount = likePostService.getLikeCount(user.getCode());
        /* "likeCount" => key값, likeCount => value값 */
        model.addAttribute("likeCount", likeCount);

        List<LikePostDTO> likedPosts = likePostService.getLikedPostsByUserCode(user.getCode());
        model.addAttribute("likedPosts", likedPosts);

        log.info("likedPosts:{}", likedPosts);
        return "views/user/likePage";
    }
    /* 내가 한 좋아요 목록 조회 end */

//    @RequestMapping(value = "/user/likedPostCount", method = RequestMethod.GET)
//    public ResponseEntity<Integer> getLikedPostCount() {
//        // 좋아요된 포스트 개수를 계산하는 로직을 수행하고 반환
//        int likedPostCount = userService.getLikedPostCount();
//        return ResponseEntity.ok(likedPostCount);
//    }

    @GetMapping("/likeInfo")
    public String getLikeInfo(Model model, @RequestParam("postCode") int postCode, @RequestParam("userCode") String userCode) {

        model.addAttribute("board_no", postCode);
        model.addAttribute("user_no", userCode);
        return "likeInfoPage"; // 좋아요 정보를 표시할 페이지의 이름
    }

    @ResponseBody
    @PostMapping("/likeUp")
    public void likeUp(@RequestBody LikeDTO likeDTO) {
        System.out.println("컨트롤러 연결 성공");
        System.out.println(likeDTO.getPostCode());
        System.out.println(likeDTO.getUserCode());
        likeService.likeUp(likeDTO);

    }

    @ResponseBody
    @PostMapping("/likeDown")
    public void likeDown(@RequestBody LikeDTO likeDTO) {
        System.out.println("좋아요 싫어요!");
        likeService.likeDown(likeDTO);
    }


}
