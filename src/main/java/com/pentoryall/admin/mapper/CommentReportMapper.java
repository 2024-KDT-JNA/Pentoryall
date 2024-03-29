package com.pentoryall.admin.mapper;
import com.pentoryall.admin.DTO.CommentReportDTO;
import com.pentoryall.admin.page.SelectCriteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface CommentReportMapper {
    
    int selectTotalCount(Map<String, String> searchMap);

    List<CommentReportDTO> selectAllCommentReportList(SelectCriteria selectCriteria);

    int noStopUser(long userCode);

    int restoreUserState(long userCode, String state);

}
