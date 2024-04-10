package com.pentoryall.point.mapper;

import com.pentoryall.point.dto.TransactionDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TransactionMapper {

    Long existsTransactionCode(TransactionDTO transaction);

    void save(TransactionDTO transaction);
}
