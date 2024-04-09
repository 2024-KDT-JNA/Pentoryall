package com.pentoryall.admin.mappers;

import com.pentoryall.admin.dtos.GenreManageDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GenreManageMapper {

    List<GenreManageDTO> selectFirstGenreList();

    List<GenreManageDTO> selectSecondGenreList();

    void addFirstGenre(String name);

    void addSecondGenre(String name);

    void deleteSecondGenre(String name);

    void updateGenre(GenreManageDTO genreDTO);
}
