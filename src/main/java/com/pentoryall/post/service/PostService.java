package com.pentoryall.post.service;

import com.pentoryall.post.dto.PostDTO;
import com.pentoryall.post.mapper.PostMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PostService {

    private final PostMapper postMapper;

    public PostService(PostMapper postMapper) {
        this.postMapper = postMapper;
    }

    @Transactional
    public void insertPost(PostDTO postDTO) {
        postMapper.insertPost(postDTO);
    }

    public PostDTO getPostInformation(long lastCode) {
        return postMapper.getPostInformation(lastCode);
    }
}
