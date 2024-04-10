package com.pentoryall.post.service;

import com.pentoryall.post.dto.ReportPostDTO;
import com.pentoryall.post.mapper.ReportPostMapper;
import org.springframework.stereotype.Service;

@Service
public class ReportPostService {
    private final ReportPostMapper reportPostMapper;

    public ReportPostService(ReportPostMapper reportPostMapper) {
        this.reportPostMapper = reportPostMapper;
    }

    public void insertReportPost(ReportPostDTO reportPostDTO) {
        reportPostMapper.insertReportPost(reportPostDTO);
    }

    public void insertCommentPost(ReportPostDTO reportPostDTO) {
        reportPostMapper.insertCommentPost(reportPostDTO);
    }
}
