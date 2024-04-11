package com.pentoryall.admin.service;

import com.pentoryall.admin.dtos.GenreManageDTO;
import com.pentoryall.admin.mappers.GenreManageMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
public class GenreManageService {

    private final GenreManageMapper genreManageMapper;

    public GenreManageService(GenreManageMapper genreManageMapper, GenreManageDTO genreManageDTO) {
        this.genreManageMapper = genreManageMapper;
    }


    public List<GenreManageDTO> selectFirstGenreList() {

        return genreManageMapper.selectFirstGenreList();
    }

    public List<GenreManageDTO> selectSecondGenreList() {
        return genreManageMapper.selectSecondGenreList();
    }


    public void addFirstGenre(String name) {
        genreManageMapper.addFirstGenre(name);
    }


    public void addSecondGenre(String name) {
        genreManageMapper.addSecondGenre(name);
    }


    public void deleteSecondGenre(String name) {
        genreManageMapper.deleteSecondGenre(name);
        System.out.println(name);
    }


    public void modifyFirstGenre(GenreManageDTO genreDTO) {
        genreManageMapper.updateGenre(genreDTO);
    }

}
