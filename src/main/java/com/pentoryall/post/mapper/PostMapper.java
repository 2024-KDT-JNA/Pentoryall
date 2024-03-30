package com.pentoryall.post.mapper;

import com.pentoryall.genreOfArt.dto.GenreOfArtDTO;
import com.pentoryall.post.dto.PostDTO;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PostMapper {

    void insertPost(PostDTO postDTO);

    PostDTO getPostInformationByPostCode(long lastCode);

    void insertGenre(GenreOfArtDTO genreOfArtDTO);
}
