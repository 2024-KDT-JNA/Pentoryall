package com.pentoryall.post.controller;

import com.pentoryall.post.dto.PostRequestDTO;
import com.pentoryall.post.service.PostService;
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
    public Long savePost(@RequestBody final PostRequestDTO params) {
        System.out.println(params);
        System.out.println(params.getContent());
        System.out.println(params.getTitle());
        System.out.println("성공");
        long result = postService.insertPost(params);
        System.out.println(result);
        return result;
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
