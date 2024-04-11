package com.pentoryall.settlement.mapper;

import com.pentoryall.settlement.dto.SettlementDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SettlementMapper {

    int selectTotalSettlementCountByUserCode(long userCode);


    List<SettlementDTO> selectSettlementsByUserCode(long userCode, int offset, int limit);
   

    void insertSettlementRequest(SettlementDTO settlement);
}
