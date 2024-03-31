package com.pentoryall.post.service;


import com.pentoryall.genreOfArt.dto.GenreOfArtDTO;
import com.pentoryall.post.dto.PostDTO;

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
}
