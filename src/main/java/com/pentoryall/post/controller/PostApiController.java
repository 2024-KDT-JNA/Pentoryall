package com.pentoryall.post.controller;

import com.pentoryall.post.dto.PostRequestDTO;
import com.pentoryall.post.service.PostService;
import jakarta.servlet.http.HttpSession;

import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PostApiController {

    private final PostService postService;

    // 게시글 저장
    @PostMapping("/posts")
    public long savePost(@RequestBody final PostRequestDTO params, HttpSession session) {
        String title = params.getTitle();
        String contents = params.getContent();
        session.setAttribute("title",title);
        session.setAttribute("contents",contents);
        return 0;
    }

}
