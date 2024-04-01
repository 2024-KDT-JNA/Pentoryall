package com.pentoryall.user.service;

import com.pentoryall.user.mapper.LikeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final LikeMapper likeMapper;

    public void likeUp(long postCode, long userCode) {
        likeMapper.likeUp(postCode, userCode);
    }

    public void likeDown(long postCode, long userCode) {
        likeMapper.likeDown(postCode, userCode);
    }
}