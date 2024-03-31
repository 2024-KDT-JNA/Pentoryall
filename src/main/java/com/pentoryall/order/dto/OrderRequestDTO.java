package com.pentoryall.order.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequestDTO {

    private Long userCode;
    private String impUid;
    private Integer amount;
    private Integer point;
}