package com.pentoryall.order.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentResponseDTO {
    String success;
    Long orderCode;
    String payType;
}
