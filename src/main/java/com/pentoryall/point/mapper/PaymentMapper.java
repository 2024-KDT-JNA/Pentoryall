package com.pentoryall.point.mapper;

import com.pentoryall.point.dto.PaymentDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PaymentMapper {

    void insertPayment(PaymentDTO payment);
}
