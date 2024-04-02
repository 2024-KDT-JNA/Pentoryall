package com.pentoryall.settlement.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserSettlementDTO {

    Long code;
    Long userCode;
    int bankCode;
    String accountNumber;
    String accountHolder;
    char isDeleted;
    LocalDateTime createDate;
}
