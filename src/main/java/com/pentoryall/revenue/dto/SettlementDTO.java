package com.pentoryall.revenue.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class SettlementDTO {

    Long code;
    int requestAmount;
    int actualAmount;
    String state; /* Enum? */
    String cause;
    LocalDateTime createDate;
    LocalDateTime processDate;

    UserSettlementDTO userSettlement;
}
