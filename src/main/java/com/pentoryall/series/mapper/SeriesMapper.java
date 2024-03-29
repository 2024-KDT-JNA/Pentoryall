package com.pentoryall.series.mapper;

import com.pentoryall.series.dto.SeriesDTO;
import org.apache.ibatis.annotations.Mapper;


import java.util.List;

@Mapper
public interface SeriesMapper {
    SeriesDTO selectSeriesByTitle(String series);

    SeriesDTO getSeriesInformationBySeriesCode(long seriesCode);

    List<SeriesDTO> getSeriesList(int i);

    long addSeriesOptions(SeriesDTO seriesDTO);

    SeriesDTO findSeriesByCode(long code);
}
