package com.pentoryall.genre.mapper;

import com.pentoryall.genre.dto.GenreDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GenreMapper {
    List<GenreDTO> getGenreList();

    List<GenreDTO> getLowerGenreList(long code);

    GenreDTO selectGenreTitle(long genreCode);

    GenreDTO getGenre(long seriesCode);

    List<GenreDTO> selectGenreList(long genreCode);

    GenreDTO selectGenre(long genreCode);
}
