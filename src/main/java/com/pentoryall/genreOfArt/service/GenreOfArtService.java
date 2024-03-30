package com.pentoryall.genreOfArt.service;

import com.pentoryall.genre.dto.GenreDTO;
import com.pentoryall.genreOfArt.dto.GenreOfArtDTO;
import com.pentoryall.genreOfArt.mapper.GenreOfArtMapper;
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

    public void deleteSeriesGenreBySeriesCode(long code) {
        genreOfArtMapper.deleteSeriesGenreBySeriesCode(code);
    }

    public void insertGenreBySeriesCode(long code,long genreCode) {
        genreOfArtMapper.insertGenreBySeriesCode(code,genreCode);
    }

    public List<GenreOfArtDTO> findGenreBySeriesCodeSeries(long code) {
        return genreOfArtMapper.findGenreBySeriesCodeSeries(code);
    }
}
