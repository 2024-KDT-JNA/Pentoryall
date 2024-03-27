package com.pentoryall.admin.mapper;

import com.pentoryall.admin.DTO.GenreManageDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GenreManageMapper {
    List<GenreManageDTO> selectAllGenreList();
}
