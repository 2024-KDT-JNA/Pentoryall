package com.pentoryall.settlement.dto;

import com.pentoryall.settlement.status.SettlementState;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class SettlementDTO {

    Long code;
    Long userSettlementCode;
    int requestAmount;
    int actualAmount;
    SettlementState state;
    String cause;
    LocalDateTime createDate;
    LocalDateTime processDate;
}
