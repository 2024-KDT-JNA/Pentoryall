package com.pentoryall.revenue.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class UserSettlementDTO {

    Long code;
    Long userCode;
    int bankCode;
    int accountNumber;
    String accountHolder;
    String isDeleted; /* Enum? */
    LocalDateTime createDate;
}
