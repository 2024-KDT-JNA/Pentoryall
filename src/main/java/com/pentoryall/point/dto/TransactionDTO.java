package com.pentoryall.point.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class TransactionDTO {

    Long code;
    Long userCode;
    Long sellerUserCode;
    Long postCode;
    Long membershipCode;
    String type; /* enum? */
    int point;
    LocalDateTime createDate;
}
