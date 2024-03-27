package com.pentoryall.admin.Service;

import com.pentoryall.admin.DTO.GenreManageDTO;
import com.pentoryall.admin.mapper.GenreManageMapper;
import com.pentoryall.admin.mapper.UserManageMapper;
import com.pentoryall.user.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
public class GenreManageService {

    private final GenreManageMapper genreManageMapper;

    public GenreManageService(GenreManageMapper genreManageMapper) {
        this.genreManageMapper = genreManageMapper;
    }


    public List<GenreManageDTO> selectAllGenreList() {
        return genreManageMapper.selectAllGenreList();
    }
}
