package com.pentoryall.admin.service;

import com.pentoryall.admin.dtos.PayManageDTO;
import com.pentoryall.admin.mappers.PayManageMapper;
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
public class PayManageService {

    private final PayManageMapper payManageMapper;

    public PayManageService(PayManageMapper payManageMapper) {
        this.payManageMapper = payManageMapper;
    }

    public Map<String, Object> payAllList(Map<String, String> searchMap, int page) {

        int totalCount = payManageMapper.selectTotalCount(searchMap);
        log.info("payAllList totalCount : {}", totalCount);

        int limit = 10;
        int buttonAmount = 5;
        SelectCriteria selectCriteria = Pagination.getSelectCriteria(page, totalCount, limit, buttonAmount, searchMap);
        log.info("payAllList selectCriteria : {}", selectCriteria);

        /* 요청 페이지와 검색 기준에 맞는 게시글을 조회해온다. */
        List<PayManageDTO> payAllList = payManageMapper.selectPayAllList(selectCriteria);
        log.info("payAllList : {}", payAllList);

        // Model에 추가할 속성 이름을 Controller에서 사용하는 이름으로 변경
        Map<String, Object> payListAndPaging = new HashMap<>();
        payListAndPaging.put("paging", selectCriteria);
        payListAndPaging.put("payAllList", payAllList);

        System.out.println(payAllList);

        return payListAndPaging;
    }

    public int payConfirmByUserCode(long userCode, String state) {
        return payManageMapper.payConfirmByUserCode(userCode, state);
    }
}
