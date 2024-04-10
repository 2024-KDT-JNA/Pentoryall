package com.pentoryall.settlement.dto;

import com.pentoryall.settlement.enums.SettlementState;
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

    public SettlementDTO(Long userSettlementCode, int requestAmount) {
        this.userSettlementCode = userSettlementCode;
        this.requestAmount = requestAmount;
        this.actualAmount = (requestAmount / 10) * 9;
    }
}
