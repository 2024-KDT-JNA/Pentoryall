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

    public List<LikePostDTO> selectLikeByPostCode(Long seriesCode, Long code) {
        return likePostMapper.selectLikeByPostCode(seriesCode,code);
    }

    public int selectLikeCountByPostCode(Long code) {
        return likePostMapper.selectLikeCountByPostCode(code);
    }

    public List<Long> selectTop5Post() {
        return likePostMapper.selectTop5Post();
    }
}
