package com.pentoryall.order.mapper;

import com.pentoryall.order.dto.PaymentDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PaymentMapper {

    void insertPayment(PaymentDTO payment);
}
