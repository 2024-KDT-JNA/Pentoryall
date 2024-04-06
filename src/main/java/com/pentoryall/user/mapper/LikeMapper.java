package com.pentoryall.user.mapper;

import com.pentoryall.user.dto.LikeDTO;
import com.pentoryall.user.dto.LikePostDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LikeMapper {
    void likeUp(LikeDTO likeDTO);

    void likeDown(LikeDTO likeDTO);


    LikeDTO selectLikeByUserAndPost(long userCode, long code);
}
