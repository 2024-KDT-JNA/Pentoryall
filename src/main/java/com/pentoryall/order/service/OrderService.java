package com.pentoryall.order.service;

import com.pentoryall.common.exception.order.OrderFailedException;
import com.pentoryall.order.dto.OrderDTO;
import com.pentoryall.order.dto.PaymentDTO;
import com.pentoryall.order.mapper.OrderMapper;
import com.pentoryall.order.mapper.PaymentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderMapper orderMapper;

    private final PaymentMapper paymentMapper;

    public OrderDTO findByOrderCode(Long orderCode) {
        return orderMapper.findByOrderCode(orderCode);
    }

    @Transactional
    public void save(OrderDTO order, PaymentDTO payment) throws OrderFailedException {
        orderMapper.save(order);

        if (order.getCode() == null) {
            throw new OrderFailedException("주문 정보 저장에 실패하였습니다.");
        }
        payment.setOrderCode(order.getCode());
        paymentMapper.save(payment);
    }
}
