package com.pentoryall.membership.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@ToString
public class MembershipJoinDTO {

    private long code;
    private long membershipCode;
    private long userCode;
    private LocalDate createDate;
    private LocalDate endDate;
    private String name;
    private int price;

}

