package com.pentoryall.user.service;

import com.pentoryall.user.dto.LikeDTO;
import com.pentoryall.user.mapper.LikeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final LikeMapper likeMapper;

    public void likeUp(LikeDTO likeDTO) {
        likeMapper.likeUp(likeDTO);
    }

    public void likeDown(LikeDTO likeDTO) {
        likeMapper.likeDown(likeDTO);
    }
}