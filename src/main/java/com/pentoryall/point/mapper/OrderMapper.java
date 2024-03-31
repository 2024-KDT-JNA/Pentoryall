package com.pentoryall.point.mapper;

import com.pentoryall.point.dto.OrderDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper {

    void insertOrder(OrderDTO order);

    OrderDTO selectByOrderCode(Long orderCode);
}
