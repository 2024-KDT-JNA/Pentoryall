package com.pentoryall.genreOfArt.service;

import com.pentoryall.genre.dto.GenreDTO;
import com.pentoryall.genreOfArt.controller.GenreRequest;
import com.pentoryall.genreOfArt.dto.GenreOfArtDTO;
import com.pentoryall.genreOfArt.mapper.GenreOfArtMapper;
import com.pentoryall.post.dto.PostDTO;
import com.pentoryall.series.dto.SeriesDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GenreOfArtService {
    private final GenreOfArtMapper genreOfArtMapper;

    public GenreOfArtService(GenreOfArtMapper genreOfArtMapper) {
        this.genreOfArtMapper = genreOfArtMapper;
    }

    @Transactional
    public void insertGenreOfArt(GenreOfArtDTO genreOfArtDTO) {
        genreOfArtMapper.insertGenreOfArt(genreOfArtDTO);
    }
    @Transactional
    public void insertSeriesGenre(GenreOfArtDTO genreOfArtDTO) {
        genreOfArtMapper.insertSeriesGenre(genreOfArtDTO);
    }

    public GenreOfArtDTO getGenre(long seriesCode) {
        return genreOfArtMapper.getGenre(seriesCode);
    }

    public List<GenreOfArtDTO> getLowerGenre(long code) {
        return genreOfArtMapper.getLowerGenre(code);
    }

    public List<GenreOfArtDTO> findGenreBySeriesCode(long code) {
        return genreOfArtMapper.findGenreBySeriesCode(code);
    }
    @Transactional
    public void deleteSeriesGenreBySeriesCode(long code) {
        genreOfArtMapper.deleteSeriesGenreBySeriesCode(code);
    }
    @Transactional
    public void insertGenreBySeriesCode(long code,long genreCode) {
        genreOfArtMapper.insertGenreBySeriesCode(code,genreCode);
    }

    public List<GenreOfArtDTO> findGenreBySeriesCodeSeries(long code) {
        return genreOfArtMapper.findGenreBySeriesCodeSeries(code);
    }

    public List<GenreOfArtDTO> getGenreInformationByPostCode(long seriesCode) {
        return genreOfArtMapper.getGenreInformationByPostCode(seriesCode);
    }

    public List<GenreOfArtDTO> selectGenreByPostCode(long code) {
        return genreOfArtMapper.selectGenreByPostCode(code);
    }
    @Transactional
    public void insertGenreBySeriesCodePost(long postCode,long code, Long genreCode) {
        genreOfArtMapper.insertGenreBySeriesCodePost(postCode,code,genreCode);
    }

    public List<GenreOfArtDTO> selectPostInSeries(long code) {
        return genreOfArtMapper.selectPostInSeries(code);
    }
    @Transactional
    public void deleteSeriesGenreByPostCode(long postCode) {
        genreOfArtMapper.deleteSeriesGenreByPostCode(postCode);
    }
@Transactional
    public void insertGenreForDTO(GenreOfArtDTO genreOfArtDTO) {
        genreOfArtMapper.insertGenreForDTO(genreOfArtDTO);
    }

    public List<GenreOfArtDTO> selectSeriesByGenre(Long genreCode) {
        return genreOfArtMapper.selectSeriesByGenre(genreCode);
    }

    public List<SeriesDTO> selectSeriesByGenreNull(Long upperGenre,Long lowerGenre) {
        return genreOfArtMapper.selectSeriesByGenreNull(upperGenre,lowerGenre);
    }

    public List<PostDTO> selectPostByGenreNull(Long upperGenre) {
        return genreOfArtMapper.selectPostByGenreNull(upperGenre);
    }

    public List<PostDTO> selectPostByGenre(Long upperGenre, Long lowerGenre) {
        return genreOfArtMapper.selectPostByGenre(upperGenre,lowerGenre);
    }

    public GenreOfArtDTO selectSeriesGenre(Long seriesCode, Long genreCode) {
        return genreOfArtMapper.selectSeriesGenre(seriesCode,genreCode);
    }

    public List<GenreOfArtDTO> selectPostNotInSeries(Long genreCode) {
        return genreOfArtMapper.selectPostNotInSeries(genreCode);
    }
}
