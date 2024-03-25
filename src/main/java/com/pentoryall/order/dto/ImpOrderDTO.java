package com.pentoryall.order.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ImpOrderDTO {

    Long code;
    Long userCode;
    int amount;
    int point;
    LocalDateTime createDate;
}
