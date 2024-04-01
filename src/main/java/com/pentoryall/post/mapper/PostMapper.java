package com.pentoryall.post.mapper;

import com.pentoryall.genreOfArt.dto.GenreOfArtDTO;
import com.pentoryall.post.dto.PostDTO;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostMapper {

    void insertPost(PostDTO postDTO);

    PostDTO getPostInformationByPostCode(long lastCode);

    void insertGenre(GenreOfArtDTO genreOfArtDTO);

    List<PostDTO> selectPostsBySeriesCode(long code);

    void updatePostService(PostDTO postDTO);
}
