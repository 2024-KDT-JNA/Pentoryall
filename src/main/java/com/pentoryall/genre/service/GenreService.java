package com.pentoryall.genre.service;

import com.pentoryall.genre.dto.GenreDTO;
import com.pentoryall.genre.mapper.GenreMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreService {

    private final GenreMapper genreMapper;

    public GenreService(GenreMapper genreMapper) {
        this.genreMapper = genreMapper;
    }

    public List<GenreDTO> getGenreList() {
        return genreMapper.getGenreList();
    }

    public List<GenreDTO> getLowerGenreList() {
        return genreMapper.getLowerGenreList();
    }
}
