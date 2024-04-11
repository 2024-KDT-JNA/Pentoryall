package com.pentoryall.point.dto;

import com.pentoryall.point.enums.TransactionType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserPurchaseDTO {

    Long code;
    Long userCode;
    Long transactionCode;
    Long postCode;
    String postTitle;
    Long membershipCode;
    String membershipName;
    TransactionType type;
    int point;
    LocalDateTime createDate;
}
