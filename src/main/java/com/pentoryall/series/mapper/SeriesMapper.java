package com.pentoryall.series.mapper;

import com.pentoryall.series.dto.SeriesDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SeriesMapper {
    SeriesDTO selectSeriesByTitle(String series);
}
