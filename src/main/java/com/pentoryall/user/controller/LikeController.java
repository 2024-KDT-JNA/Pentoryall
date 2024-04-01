package com.pentoryall.user.controller;

import com.pentoryall.user.dto.LikeDTO;
import com.pentoryall.user.service.LikeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class LikeController {

    private final LikeService likeService;

    @GetMapping("/like")
    public String likePage() {
        return "views/user/likePage";
    }
    
    @ResponseBody
    @PostMapping("/likeUp")
    public void likeUp(@RequestBody LikeDTO likeDTO) {
        System.out.println("컨트롤러 연결 성공");
        System.out.println(likeDTO.getPostCode());
        System.out.println(likeDTO.getUserCode());
        likeService.likeUp(likeDTO.getPostCode(), likeDTO.getUserCode());

    }

    @ResponseBody
    @PostMapping("/likeDown")
    public void likeDown(@RequestBody LikeDTO likeDTO) {
        System.out.println("좋아요 싫어요!");
        likeService.likeDown(likeDTO.getPostCode(), likeDTO.getUserCode());
    }
}
