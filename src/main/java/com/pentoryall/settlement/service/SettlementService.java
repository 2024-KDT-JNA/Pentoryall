package com.pentoryall.settlement.service;

import com.pentoryall.common.page.Pagination;
import com.pentoryall.common.page.SelectCriteria;
import com.pentoryall.settlement.dto.SettlementDTO;
import com.pentoryall.settlement.dto.UserRevenueDTO;
import com.pentoryall.settlement.mapper.SettlementMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SettlementService {

    private final SettlementMapper settlementMapper;

    public Map<String, Object> selectSettlementListWithPagingByUserCode(int page, long userCode) {

        int totalCount = settlementMapper.selectTotalSettlementCountByUserCode(userCode);
        SelectCriteria selectCriteria = Pagination.getSelectCriteria(page, totalCount);

        Map<String, Object> settlementListAndPaging = new HashMap<>();
        List<SettlementDTO> settlementList = settlementMapper.selectSettlementsByUserCode(userCode, selectCriteria.getOffset(), selectCriteria.getLimit());
        settlementListAndPaging.put("paging", selectCriteria);
        settlementListAndPaging.put("settlementList", settlementList);

        return settlementListAndPaging;
    }

    public Map<String, Object> selectRevenueListWithPagingByUserCode(int page, long userCode) {

        int totalCount = settlementMapper.selectTotalRevenueCountByUserCode(userCode);
        SelectCriteria selectCriteria = Pagination.getSelectCriteria(page, totalCount);

        Map<String, Object> revenueListAndPaging = new HashMap<>();
        List<UserRevenueDTO> revenueList = settlementMapper.selectRevenuesByUserCode(userCode, selectCriteria.getOffset(), selectCriteria.getLimit());
        revenueListAndPaging.put("paging", selectCriteria);
        revenueListAndPaging.put("revenueList", revenueList);

        return revenueListAndPaging;
    }
}
