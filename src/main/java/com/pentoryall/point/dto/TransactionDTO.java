package com.pentoryall.point.dto;

import com.pentoryall.point.enums.TransactionType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@RequiredArgsConstructor
public class TransactionDTO {

    Long code;
    Long userCode;
    Long sellerUserCode;
    Long postCode;
    Long membershipCode;
    TransactionType type;
    int point;
    LocalDateTime createDate;
}
