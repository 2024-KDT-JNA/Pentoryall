package com.pentoryall.user.service;

import com.pentoryall.user.dto.LikePostDTO;
import com.pentoryall.user.mapper.LikePostMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LikePostService {

    private final LikePostMapper likePostMapper;

    public List<LikePostDTO> getLikedPostsByUserCode(Long userCode) {
        System.out.println("userCode = " + userCode);
        return likePostMapper.getLikedPostsByUserCode(userCode);
    }

    public int getLikeCount(long userCode) {
        System.out.println("userCode = " + userCode);
        return likePostMapper.getLikeCount(userCode);
    }
}