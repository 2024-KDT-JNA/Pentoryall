package com.pentoryall.order.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class OrderDTO {

    Long code;
    Long userCode;
    int amount;
    int point;
    LocalDateTime createDate;

    public OrderDTO(PaymentRequestDTO orderPayment) {
        this.userCode = orderPayment.getUserCode();
        this.amount = orderPayment.getAmount();
        this.point = orderPayment.getPoint();
    }
}
