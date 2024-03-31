package com.pentoryall.order.mapper;

import com.pentoryall.order.dto.PaymentDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@SpringBootTest
class PaymentMapperTests {

    @Autowired
    private PaymentMapper paymentMapper;

    @Test
    @DisplayName("결제 정보 저장 테스트 - 성공")
    @Transactional
    public void insertPaymentSuccess() {

        // given
        PaymentDTO payment = new PaymentDTO();
        payment.setOrderCode(7L);
        payment.setImpUid("imp_uid_123535324");
        payment.setCreateDate(LocalDateTime.now());

        // when
        paymentMapper.insertPayment(payment);

        // then
        Assertions.assertNotNull(payment.getCode());
    }

    @Test
    @DisplayName("결제 정보 저장 테스트 - 실패")
    @Transactional
    public void insertPaymentFailed() {

        // given
        PaymentDTO payment = new PaymentDTO();
        payment.setOrderCode(0L);
        payment.setImpUid("imp_uid_123535324");
        payment.setCreateDate(LocalDateTime.now());

        // when
        paymentMapper.insertPayment(payment);

        // then
        Assertions.assertNotNull(payment.getCode());
    }
}