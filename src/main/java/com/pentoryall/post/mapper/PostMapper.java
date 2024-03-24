package com.pentoryall.post.mapper;

import com.pentoryall.post.dto.PostRequestDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PostMapper {


    Long insertPost(PostRequestDTO params);
}
