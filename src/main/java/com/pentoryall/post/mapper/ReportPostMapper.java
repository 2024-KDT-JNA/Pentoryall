package com.pentoryall.post.mapper;

import com.pentoryall.post.dto.ReportPostDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReportPostMapper {
    void insertReportPost(ReportPostDTO report);

    void insertCommentPost(ReportPostDTO reportPostDTO);
}
