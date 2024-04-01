package com.pentoryall.series.mapper;

import com.pentoryall.series.dto.SeriesDTO;
import org.apache.ibatis.annotations.Mapper;


import java.util.List;

@Mapper
public interface SeriesMapper {
    SeriesDTO selectSeriesByTitle(long seriesno);

    SeriesDTO getSeriesInformationBySeriesCode(long seriesCode);

    List<SeriesDTO> getSeriesList(int i);

    long addSeriesOptions(SeriesDTO seriesDTO);

    SeriesDTO findSeriesByCode(long code);

    SeriesDTO selectRecentSeriesCode();

    void updateSeries(SeriesDTO seriesDTO,long code);

    void deleteSeries(long code);

    List<SeriesDTO> selectSeriesList();
}
