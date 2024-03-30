package com.pentoryall.genreOfArt.mapper;

import com.pentoryall.genreOfArt.dto.GenreOfArtDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GenreOfArtMapper {
    void insertGenreOfArt(GenreOfArtDTO genreOfArtDTO);

    void insertSeriesGenre(GenreOfArtDTO genreOfArtDTO);

    GenreOfArtDTO getGenre(long seriesCode);

    List<GenreOfArtDTO> getLowerGenre(long code);

    List<GenreOfArtDTO> findGenreBySeriesCode(long code);

    void deleteSeriesGenreBySeriesCode(long code);

    void insertGenreBySeriesCode(long code,long genreCode);

    List<GenreOfArtDTO> findGenreBySeriesCodeSeries(long code);
}