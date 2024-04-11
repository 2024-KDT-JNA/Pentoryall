package com.pentoryall.post.service;


import com.pentoryall.comment.dto.CommentDetailDTO;
import com.pentoryall.genreOfArt.dto.GenreOfArtDTO;
import com.pentoryall.post.dto.PostDTO;
import com.pentoryall.post.dto.ValidatePostDTO;
import com.pentoryall.post.mapper.PostMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PostService {

    private final PostMapper postMapper;

    public PostService(PostMapper postMapper) {
        this.postMapper = postMapper;
    }

    @Transactional
    public void insertPost(PostDTO postDTO) {
        postMapper.insertPost(postDTO);
    }

    public PostDTO getPostInformationByPostCode(long code) {
        return postMapper.getPostInformationByPostCode(code);
    }

    @Transactional
    public void insertGenre(GenreOfArtDTO genreOfArtDTO) {
        postMapper.insertGenre(genreOfArtDTO);
    }

    public List<PostDTO> selectPostsBySeriesCode(long code) {
        return postMapper.selectPostsBySeriesCode(code);
    }

    @Transactional
    public void updatePostService(PostDTO postDTO) {
        postMapper.updatePostService(postDTO);
    }

    @Transactional
    public void deletePostByPostCode(long code) {
        postMapper.deletePostByPostCode(code);
    }

    @Transactional
    public void deleteSeriesBySeriesCode(long code) {
        postMapper.deleteSeriesBySeriesCode(code);
    }

    public List<PostDTO> selectPostList() {
        return postMapper.selectPostList();
    }

    public List<PostDTO> getSeriesListByWord(String word) {
        return postMapper.getSeriesListByWord(word);
    }

    public List<PostDTO> selectPostByUserCode(Long userCode) {
        return postMapper.selectPostByUserCode(userCode);
    }

    @Transactional
    public void addComment(CommentDetailDTO commentAdd) {
        postMapper.addComment(commentAdd);
    }

    public PostDTO getLatestPost() {
        return postMapper.getLatestPost();
    }

    public void updateViews(long code) {
        postMapper.updateViews(code);
    }

    public ValidatePostDTO selectPostAndSeriesByPostCode(long postCode) { return postMapper.selectPostAndSeriesByPostCode(postCode); }

    public PostDTO selectFirstPostBySeriesCode(long code) {
        return postMapper.selectFirstPostBySeriesCode(code);
    }
}
