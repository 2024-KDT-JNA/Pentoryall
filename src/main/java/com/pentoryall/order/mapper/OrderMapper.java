package com.pentoryall.order.mapper;

import com.pentoryall.order.dto.OrderDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper {

    void save(OrderDTO order);

    OrderDTO findByOrderCode(Long orderCode);
}
