package com.pentoryall.admin.mappers;

import com.pentoryall.admin.dtos.CommentReportDTO;
import com.pentoryall.common.page.SelectCriteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface CommentReportMapper {

    int selectTotalCount(Map<String, String> searchMap);

    List<CommentReportDTO> selectAllCommentReportList(SelectCriteria selectCriteria);

    int restoreUserState(long userCode, String state);

}