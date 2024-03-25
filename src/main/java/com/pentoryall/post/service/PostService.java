package com.pentoryall.post.service;

import com.pentoryall.post.dto.PostRequestDTO;
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
    public Long insertPost(PostRequestDTO params) {
        return postMapper.insertPost(params);
    }
}
