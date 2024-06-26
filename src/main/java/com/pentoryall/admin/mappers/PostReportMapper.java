package com.pentoryall.admin.mappers;

import com.pentoryall.admin.dtos.PostReportDTO;
import com.pentoryall.common.page.SelectCriteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface PostReportMapper {

    int selectTotalCount(Map<String, String> searchMap);

    List<PostReportDTO> selectAllPostReportList(SelectCriteria selectCriteria);

    int deleteByPostCode(long postCode, String isDeleted);
}
