package com.pentoryall.settlement.mapper;

import com.pentoryall.settlement.dto.SettlementDTO;
import com.pentoryall.settlement.dto.UserRevenueDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SettlementMapper {

    int selectTotalSettlementCountByUserCode(long userCode);

    int selectTotalRevenueCountByUserCode(long userCode);

    List<SettlementDTO> selectSettlementsByUserCode(long userCode, int offset, int limit);

    List<UserRevenueDTO> selectRevenuesByUserCode(long userCode, int offset, int limit);
}
