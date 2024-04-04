package com.pentoryall.settlement.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@ToString
public class UserSettlementDTO {

    Long code;
    Long userCode;
    String bankCode;
    String accountNumber;
    String accountHolder;
    char isDeleted;
    LocalDateTime createDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserSettlementDTO that = (UserSettlementDTO) o;
        return Objects.equals(bankCode, that.bankCode) && Objects.equals(userCode, that.userCode) && Objects.equals(accountNumber, that.accountNumber) && Objects.equals(accountHolder, that.accountHolder);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userCode, bankCode, accountNumber, accountHolder);
    }
}
