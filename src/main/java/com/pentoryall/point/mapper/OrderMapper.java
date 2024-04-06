package com.pentoryall.point.mapper;

import com.pentoryall.point.dto.OrderDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {


    OrderDTO selectByOrderCode(long orderCode);

    int selectTotalCountByUserCode(long orderCode);

    List<OrderDTO> selectAllByUserCode(long userCode, int offset, int limit);

    void insertOrder(OrderDTO order);

}
