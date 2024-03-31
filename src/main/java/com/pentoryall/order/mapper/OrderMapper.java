package com.pentoryall.order.mapper;

import com.pentoryall.order.dto.OrderDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper {

    void insertOrder(OrderDTO order);

    OrderDTO selectByOrderCode(Long orderCode);
}
