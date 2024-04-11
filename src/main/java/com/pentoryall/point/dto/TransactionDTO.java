package com.pentoryall.point.dto;

import com.pentoryall.membership.dto.MembershipDTO;
import com.pentoryall.point.enums.TransactionType;
import com.pentoryall.post.dto.PostDTO;
import com.pentoryall.user.dto.UserDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class TransactionDTO {

    Long code;
    Long userCode;
    Long sellerUserCode;
    Long postCode;
    Long membershipCode;
    TransactionType type;
    int point;
    LocalDateTime createDate;


    public TransactionDTO(Long userCode, Long productCode, TransactionType type) {
        this.userCode = userCode;
        this.type = type;
        if (type.equals(TransactionType.POST)) {
            this.postCode = productCode;
        }
        if (type.equals(TransactionType.MEMBERSHIP)) {
            this.membershipCode = productCode;
        }
    }

    public TransactionDTO(UserDTO buyer, PostDTO post) {
        this.userCode = buyer.getCode();
        this.sellerUserCode = post.getUserCode();
        this.postCode = post.getCode();
        this.type = TransactionType.POST;
        this.point = post.getPrice();
    }

    public TransactionDTO(UserDTO buyer, MembershipDTO membership) {
        this.userCode = buyer.getCode();
        this.sellerUserCode = membership.getUserCode();
        this.membershipCode = membership.getCode();
        this.type = TransactionType.MEMBERSHIP;
        this.point = membership.getPrice();
    }
}
