package com.pentoryall.user.mapper;

import com.pentoryall.user.dto.LikePostDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LikePostMapper {

    List<LikePostDTO> getLikedPostsByUserCode(long userCode);

    int getLikeCount(long userCode);
}
