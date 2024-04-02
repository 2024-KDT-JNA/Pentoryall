package com.pentoryall.settlement.mapper;

import com.pentoryall.settlement.dto.UserSettlementDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserSettlementMapper {

    UserSettlementDTO selectByUserCode(long userCode);
}
