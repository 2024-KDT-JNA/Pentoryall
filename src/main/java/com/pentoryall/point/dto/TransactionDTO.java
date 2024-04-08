package com.pentoryall.point.dto;

import com.pentoryall.membership.dto.MembershipDTO;
import com.pentoryall.point.enums.TransactionType;
import com.pentoryall.post.dto.PostDTO;
import com.pentoryall.user.dto.UserDTO;
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
    TransactionType type;
    int point;
    LocalDateTime createDate;

    public TransactionDTO(PostDTO post, UserDTO buyer) {
        this.userCode = buyer.getCode();
        this.sellerUserCode = post.getUserCode();
        this.postCode = post.getCode();
        this.type = TransactionType.POST;
        // FIXME
        // this.point = post.getPrice();
        this.point = Math.toIntExact(post.getPrice());
    }

    public TransactionDTO(MembershipDTO membership, UserDTO buyer) {
        this.userCode = buyer.getCode();
        this.sellerUserCode = membership.getUserCode();
        this.membershipCode = membership.getCode();
        this.type = TransactionType.MEMBERSHIP;
        this.point = membership.getPrice();
    }
}
