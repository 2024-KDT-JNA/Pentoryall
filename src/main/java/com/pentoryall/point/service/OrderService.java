package com.pentoryall.point.service;

import com.pentoryall.point.dto.OrderDTO;
import com.pentoryall.point.dto.PaymentDTO;
import com.pentoryall.point.mapper.OrderMapper;
import com.pentoryall.point.mapper.PaymentMapper;
import com.pentoryall.user.dto.UserDTO;
import com.pentoryall.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderMapper orderMapper;

    private final PaymentMapper paymentMapper;

    private final UserMapper userMapper;

    public OrderDTO selectOrderByCode(Long orderCode) {
        return orderMapper.selectByOrderCode(orderCode);
    }

    @Transactional
    public void savePointChargeInformation(OrderDTO order, PaymentDTO payment) {

        /* 주문 정보 저장 */
        orderMapper.insertOrder(order);

        /* 결제 정보 저장 */
        payment.setOrderCode(order.getCode());
        paymentMapper.insertPayment(payment);

        /* 회원 포인트 갱신 */
        UserDTO user = userMapper.getUserInformationByUserCode(order.getUserCode());
        user.setPoint(user.getPoint() + order.getPoint());
        userMapper.updatePointByUserCode(user);
    }
}
