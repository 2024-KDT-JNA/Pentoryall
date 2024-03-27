package com.pentoryall.post.controller;

import com.pentoryall.post.dto.PostRequestDTO;
import com.pentoryall.post.service.PostService;
import jakarta.servlet.http.HttpSession;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostApiController {

    private final PostService postService;

    // 게시글 저장
    @PostMapping
    public long savePost(@RequestBody final PostRequestDTO params, HttpSession session) {
        String title = params.getTitle();
        String contents = params.getContent();
        session.setAttribute("title",title);
        session.setAttribute("contents",contents);
        return 0;

    }


}
