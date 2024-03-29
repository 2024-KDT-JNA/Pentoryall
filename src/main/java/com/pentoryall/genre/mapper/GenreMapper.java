package com.pentoryall.genre.mapper;

import com.pentoryall.genre.dto.GenreDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GenreMapper {
    List<GenreDTO> getGenreList();

    List<GenreDTO> getLowerGenreList();
}
