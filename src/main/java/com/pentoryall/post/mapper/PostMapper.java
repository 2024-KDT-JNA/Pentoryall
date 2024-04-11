package com.pentoryall.post.mapper;

import com.pentoryall.comment.dto.CommentDetailDTO;
import com.pentoryall.genreOfArt.dto.GenreOfArtDTO;
import com.pentoryall.post.dto.PostDTO;
import com.pentoryall.post.dto.PostSeriesDTO;
import com.pentoryall.post.dto.ValidatePostDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostMapper {

    void insertPost(PostDTO postDTO);

    PostDTO getPostInformationByPostCode(long code);

    void insertGenre(GenreOfArtDTO genreOfArtDTO);

    List<PostDTO> selectPostsBySeriesCode(long code);

    void updatePostService(PostDTO postDTO);

    void deletePostByPostCode(long code);

    void deleteSeriesBySeriesCode(long code);

    List<PostDTO> selectPostList();

    List<PostDTO> getSeriesListByWord(String word);

    List<PostDTO> selectPostByUserCode(Long userCode);

    void addComment(CommentDetailDTO commentAdd);

    PostDTO getLatestPost();

    void updateViews(long code);

    ValidatePostDTO selectPostAndSeriesByPostCode(long postCode);

    PostDTO selectFirstPostBySeriesCode(long code);

    PostSeriesDTO getPostInformationByPost(Long code);
}
