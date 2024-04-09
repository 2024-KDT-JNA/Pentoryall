package com.pentoryall.series.service;

import com.pentoryall.genreOfArt.controller.GenreRequest;
import com.pentoryall.series.dto.SeriesDTO;
import com.pentoryall.series.mapper.SeriesMapper;
import com.pentoryall.user.dto.UserDTO;
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

    public List<SeriesDTO> getSeriesList(long code) {
        return seriesMapper.getSeriesList(code);
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

    public void deleteSeries(long code) {
        seriesMapper.deleteSeries(code);
    }


    public List<SeriesDTO> selectSeriesList() {
        return seriesMapper.selectSeriesList();
    }

    public List<SeriesDTO> getSeriesListByWord(String word) {
        return seriesMapper.getSeriesListByWord(word);
    }


    public List<SeriesDTO> selectSeriesByUserCode(Long code) {
        return seriesMapper.selectSeriesByUserCode(code);
    }

    public List<SeriesDTO> selectAllSeries() {
        return seriesMapper.selectAllSeries();
    }

    public SeriesDTO selectLatestCode() {
       return  seriesMapper.selectLatestCode();
    }

    public SeriesDTO selectSeriesByUser(long code) {
        return seriesMapper.selectSeriesByUser(code);
    }
}
