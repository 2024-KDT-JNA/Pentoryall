package com.pentoryall.settlement.dto;

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
public class UserRevenueDTO {

    Long buyerCode;
    Long sellerCode;
    Long transactionCode;
    Long postCode;
    String postTitle;
    Long membershipCode;
    String membershipName;
    TransactionType type;
    int point;
    LocalDateTime createDate;
}
