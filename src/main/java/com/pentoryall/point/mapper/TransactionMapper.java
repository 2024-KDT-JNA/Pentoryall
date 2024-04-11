package com.pentoryall.point.mapper;


import com.pentoryall.point.dto.TransactionDTO;
import com.pentoryall.point.dto.UserPurchaseDTO;
import com.pentoryall.settlement.dto.UserRevenueDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TransactionMapper {

    Long existsTransactionCode(TransactionDTO transaction);

    int selectPurchaseCountByUserCode(long userCode);

    int selectTotalRevenueCountByUserCode(long userCode);

    List<UserRevenueDTO> selectRevenuesByUserCode(long userCode, int offset, int limit);

    List<UserPurchaseDTO> selectPurchaseAllByUserCode(long userCode, int offset, int limit);

    void save(TransactionDTO transaction);
}
