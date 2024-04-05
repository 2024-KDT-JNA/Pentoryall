package com.pentoryall.membership.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class MembershipJoinDTO {

    private long code;
    private long membershipCode;
    private long userCode;
    private Date createDate;
    private Date endDate;

}

