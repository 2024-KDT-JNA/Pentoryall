package com.pentoryall.settlement.service;

import com.pentoryall.settlement.dto.UserSettlementDTO;
import com.pentoryall.settlement.mapper.UserSettlementMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserSettlementService {

    private final UserSettlementMapper userSettlementMapper;

    public UserSettlementDTO selectByUserCode(long userCode) {
        return userSettlementMapper.selectByUserCode(userCode);
    }

    @Transactional
    public void insertNewUserSettlement(UserSettlementDTO userSettlement) {

        /* 마지막으로 입력된 값 찾기 */
        long lastInsertCode = userSettlementMapper.selectLastCodeByUserCode(userSettlement.getUserCode());
        if (lastInsertCode > 0) {
            userSettlementMapper.deleteByUserSettlementCode(lastInsertCode);
        }


        userSettlementMapper.insertUserSettlement(userSettlement);
    }
}
