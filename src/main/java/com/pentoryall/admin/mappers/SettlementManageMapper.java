package com.pentoryall.admin.mappers;

import com.pentoryall.admin.dtos.PayManageDTO;
import com.pentoryall.common.page.SelectCriteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface SettlementManageMapper {

    int selectTotalCount(Map<String, String> searchMap);

    List<PayManageDTO> selectPayAllList(SelectCriteria selectCriteria);

    int payConfirmByUserCode(long userCode, String state);
}
