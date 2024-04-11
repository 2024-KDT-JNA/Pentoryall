package com.pentoryall.settlement.service;

import com.pentoryall.common.page.Pagination;
import com.pentoryall.common.page.SelectCriteria;
import com.pentoryall.point.mapper.TransactionMapper;
import com.pentoryall.settlement.dto.SettlementDTO;
import com.pentoryall.settlement.dto.UserRevenueDTO;
import com.pentoryall.settlement.mapper.SettlementMapper;
import com.pentoryall.user.dto.UserDTO;
import com.pentoryall.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SettlementService {

    private final SettlementMapper settlementMapper;

    private final TransactionMapper transactionMapper;

    private final UserMapper userMapper;

    @Transactional
    public void insertSettlementRequest(Long userCode, SettlementDTO settlement) {

        settlementMapper.insertSettlementRequest(settlement);

        if (settlement.getCode() > 0) {
            UserDTO selectedUser = userMapper.getUserInformationByUserCode(userCode);
            selectedUser.setRevenue(selectedUser.getRevenue() - settlement.getRequestAmount());
            userMapper.updateRevenueByUserCode(selectedUser);
        }
    }

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

        int totalCount = transactionMapper.selectTotalRevenueCountByUserCode(userCode);
        SelectCriteria selectCriteria = Pagination.getSelectCriteria(page, totalCount);

        Map<String, Object> revenueListAndPaging = new HashMap<>();
        List<UserRevenueDTO> revenueList = transactionMapper.selectRevenuesByUserCode(userCode, selectCriteria.getOffset(), selectCriteria.getLimit());
        revenueListAndPaging.put("paging", selectCriteria);
        revenueListAndPaging.put("revenueList", revenueList);

        return revenueListAndPaging;
    }
}
