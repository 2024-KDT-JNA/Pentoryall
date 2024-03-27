package com.pentoryall.series.service;

import com.pentoryall.series.dto.SeriesDTO;
import com.pentoryall.series.mapper.SeriesMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeriesService {
    private final SeriesMapper seriesMapper;

    public SeriesService(SeriesMapper seriesMapper) {
        this.seriesMapper = seriesMapper;
    }

    public SeriesDTO selectSeriesByTitle(String series) {
        return seriesMapper.selectSeriesByTitle(series);
    }


    public SeriesDTO getSeriesInformationBySeriesCode(long seriesCode) {
        return seriesMapper.getSeriesInformationBySeriesCode(seriesCode);
    }

    public List<SeriesDTO> getSeriesList(int i) {
        return seriesMapper.getSeriesList(i);
    }

    public void addSeriesOptions(SeriesDTO seriesDTO) {
         seriesMapper.addSeriesOptions(seriesDTO);
    }

    public SeriesDTO findSeriesByCode(long code) {
        return seriesMapper.findSeriesByCode(code);
    }
}
