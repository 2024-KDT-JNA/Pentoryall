package com.pentoryall.user.mapper;

import com.pentoryall.user.dto.LikeDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LikeMapper {
    void likeUp(LikeDTO likeDTO);

    void likeDown(LikeDTO likeDTO);
}
