package com.pentoryall.admin.service;

import com.pentoryall.admin.dtos.PostReportDTO;
import com.pentoryall.admin.mappers.PostReportMapper;
import com.pentoryall.common.page.Pagination;
import com.pentoryall.common.page.SelectCriteria;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@Transactional
public class PostReportService {

    private final PostReportMapper postReportMapper;

    public PostReportService(PostReportMapper postReportMapper) {
        this.postReportMapper = postReportMapper;
    }

    public Map<String, Object> selectAllPostReportList(Map<String, String> searchMap, int page) {
        /* 1. 전체 게시글 수 확인 (검색어가 있는 경우 포함) => 페이징 처리를 위해 */
        int totalCount = postReportMapper.selectTotalCount(searchMap);
        log.info("postReportList totalCount : {}", totalCount);

        /* 2. 페이징 처리와 연관 된 값을 계산하여 SelectCriteria 타입의 객체에 담는다. */
        int limit = 10;         // 한 페이지에 보여줄 게시물의 수
        int buttonAmount = 5;   // 한 번에 보여질 페이징 버튼의 수
        SelectCriteria selectCriteria = Pagination.getSelectCriteria(page, totalCount, limit, buttonAmount, searchMap);
        log.info("postReportList selectCriteria : {}", selectCriteria);

        /* 3. 요청 페이지와 검색 기준에 맞는 게시글을 조회해온다. */
        List<PostReportDTO> postReportList = postReportMapper.selectAllPostReportList(selectCriteria);
        log.info("postReportList : {}", postReportList);

        Map<String, Object> postListAndPaging = new HashMap<>();
        postListAndPaging.put("paging", selectCriteria);
        postListAndPaging.put("postReportList", postReportList);

        return postListAndPaging;
    }


    public int deleteByPostCode(long postCode, String isDeleted) {

        System.out.println("서비스 postCode : " + postCode);
        return postReportMapper.deleteByPostCode(postCode, isDeleted);
    }
}
