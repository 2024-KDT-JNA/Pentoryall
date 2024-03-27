package com.pentoryall.order.mapper;

import com.pentoryall.PentoryallApplication;
import com.pentoryall.order.dto.PaymentDTO;
import groovy.util.logging.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDateTime;

@Slf4j
@SpringBootTest
@ContextConfiguration(classes = {PentoryallApplication.class})
class PaymentMapperTests {

    @Autowired
    private PaymentMapper paymentMapper;


    @Test
    @DisplayName("결제 정보 저장 테스트 - 성공")
    public void savePaymentTest() {

        // given
        PaymentDTO payment = new PaymentDTO();
        payment.setOrderCode(9L);
        payment.setImpUid("imp_uid_123535324");
        payment.setCreateDate(LocalDateTime.now());

        // when
        paymentMapper.save(payment);

        // then
//        System.out.println("payment :: " + payment.getCode());
    }
}