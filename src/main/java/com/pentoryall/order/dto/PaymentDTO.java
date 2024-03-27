package com.pentoryall.order.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class PaymentDTO {

    Long code;
    Long orderCode;
    String impUid;
    LocalDateTime createDate;

    public PaymentDTO(PaymentRequestDTO orderPayment) {
        this.impUid = orderPayment.getImpUid();
        this.createDate = orderPayment.getPaidAt();
    }
}
