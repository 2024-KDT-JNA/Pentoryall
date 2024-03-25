package com.pentoryall.series.service;

import com.pentoryall.series.dto.SeriesDTO;
import com.pentoryall.series.mapper.SeriesMapper;
import org.springframework.stereotype.Service;

@Service
public class SeriesService {
    private final SeriesMapper seriesMapper;

    public SeriesService(SeriesMapper seriesMapper) {
        this.seriesMapper = seriesMapper;
    }

    public SeriesDTO selectSeriesByTitle(String series) {
        return seriesMapper.selectSeriesByTitle(series);
    }
}
