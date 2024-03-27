package com.pentoryall.order.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PaymentRequestDTO {

    Long userCode;
    String impUid;
    Integer amount;
    Integer point;
    LocalDateTime paidAt;
}