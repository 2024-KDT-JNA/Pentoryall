package com.pentoryall.user.mapper;

import com.pentoryall.user.dto.LikeDTO;
import com.pentoryall.user.dto.LikePostDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LikeMapper {
    void likeUp(LikeDTO likeDTO);

    void likeDown(LikeDTO likeDTO);


    LikeDTO selectLikeByUserAndPost(long userCode, long code);

    List<LikeDTO> selectLikeByPostCode(Long code);

    void deleteLikeByPostCode(long code);

    List<LikeDTO> getLikeByUserCode(long code);
}
