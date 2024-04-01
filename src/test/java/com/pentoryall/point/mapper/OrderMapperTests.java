package com.pentoryall.point.mapper;

import com.pentoryall.PentoryallApplication;
import com.pentoryall.point.dto.OrderDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@ContextConfiguration(classes = { PentoryallApplication.class })
class OrderMapperTests {

    @Autowired
    private OrderMapper orderMapper;

    @Test
    @DisplayName("주문 정보 저장 후 주문번호 조회 테스트 - 성공")
    @Transactional
    public void insertOrderSuccess() {

        // given
        OrderDTO order = new OrderDTO();
        order.setUserCode(1L);
        order.setAmount(5000);
        order.setPoint(5000);

        // when
        orderMapper.insertOrder(order);

        // then
        Assertions.assertNotNull(order.getCode());
    }


    @Test
    @DisplayName("주문 정보 저장 후 주문번호 조회 테스트 - 실패")
    @Transactional
    public void insertOrderFailed() {

        // given
        OrderDTO order = new OrderDTO();
        /* 존재하지 않는 회원 코드 */
        order.setUserCode(0L);
        order.setAmount(5000);
        order.setPoint(5000);

        // when
        orderMapper.insertOrder(order);

        // then
        Assertions.assertNotNull(order.getCode());
    }
}