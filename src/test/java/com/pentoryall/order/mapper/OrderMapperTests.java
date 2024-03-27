package com.pentoryall.order.mapper;

import com.pentoryall.PentoryallApplication;
import com.pentoryall.order.dto.OrderDTO;
import groovy.util.logging.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@Slf4j
@SpringBootTest
@ContextConfiguration(classes = {PentoryallApplication.class})
class OrderMapperTests {

    @Autowired
    private OrderMapper orderMapper;

    @Test
    @DisplayName("주문 정보 저장 후 주문번호 조회 테스트 - 성공")
    public void saveOrderTest() {

        // given
        OrderDTO order = new OrderDTO();
        order.setUserCode(1L);
        order.setAmount(5000);
        order.setPoint(5000);

        // when
        orderMapper.save(order);

        // then
        System.out.println("orderCode :: " + order.getCode());
    }
}