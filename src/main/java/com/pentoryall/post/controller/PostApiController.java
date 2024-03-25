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
//        long result = postService.insertPost(params);
//        System.out.println(result);
        return 0;

    }

    // 게시글 상세정보 조회
//    @GetMapping("/{id}")
//    public PostResponse findPostById(@PathVariable final Long id) {
//        return postService.findPostById(id);
//    }
//
//    // 게시글 목록 조회
//    @GetMapping
//    public List<PostResponse> findAllPost() {
//        return postService.findAllPost();
//    }

}
