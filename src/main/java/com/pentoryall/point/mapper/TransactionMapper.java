package com.pentoryall.point.mapper;

import com.pentoryall.point.dto.TransactionDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TransactionMapper {

    void save(TransactionDTO transaction);
}
