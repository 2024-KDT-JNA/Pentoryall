package com.pentoryall.user.service;

import com.pentoryall.user.dto.LikeDTO;
import com.pentoryall.user.dto.LikePostDTO;
import com.pentoryall.user.mapper.LikeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public LikeDTO selectLikeByUserAndPost(long userCode, long code) {
        return likeMapper.selectLikeByUserAndPost(userCode,code);
    }

    public List<LikeDTO> selectLikeByPostCode(Long code) {
        return likeMapper.selectLikeByPostCode(code);
    }

    public void deleteLikeByPostCode(long code) {
        likeMapper.deleteLikeByPostCode(code);
    }

    public List<LikeDTO> getLikeByUserCode(long code) {
        return likeMapper.getLikeByUserCode(code);
    }
}