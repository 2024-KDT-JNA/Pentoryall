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

    public SeriesDTO selectSeriesByTitle(long seriesno) {
        return seriesMapper.selectSeriesByTitle(seriesno);
    }


    public SeriesDTO getSeriesInformationBySeriesCode(long seriesCode) {
        return seriesMapper.getSeriesInformationBySeriesCode(seriesCode);
    }

    public List<SeriesDTO> getSeriesList(int i) {
        return seriesMapper.getSeriesList(i);
    }

    public long addSeriesOptions(SeriesDTO seriesDTO) {
         return seriesMapper.addSeriesOptions(seriesDTO);
    }

    public SeriesDTO findSeriesByCode(long code) {
        return seriesMapper.findSeriesByCode(code);
    }

    public SeriesDTO selectRecentSeriesCode() {
        return seriesMapper.selectRecentSeriesCode();
    }

    public void updateSeries(SeriesDTO seriesDTO,long code) {
        seriesMapper.updateSeries(seriesDTO,code);
    }
}
