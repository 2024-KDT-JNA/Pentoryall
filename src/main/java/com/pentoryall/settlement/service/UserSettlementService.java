package com.pentoryall.settlement.service;

import com.pentoryall.settlement.dto.UserSettlementDTO;
import com.pentoryall.settlement.mapper.UserSettlementMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserSettlementService {

    private final UserSettlementMapper userSettlementMapper;

    public UserSettlementDTO selectByUserCode(long userCode) {
        return userSettlementMapper.selectByUserCode(userCode);
    }

    public void insertOrDeleteUserSettlement() { }
}
