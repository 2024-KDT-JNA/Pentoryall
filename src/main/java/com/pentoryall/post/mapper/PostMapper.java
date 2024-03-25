package com.pentoryall.post.mapper;

import com.pentoryall.post.dto.PostDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PostMapper {

    void insertPost(PostDTO postDTO);

    PostDTO getPostInformationByPostCode(long lastCode);
}
