package com.pentoryall.membership.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class MembershipDTO {
    private long code;
    private long userCode;
    private String name;
    private String introduction;
    private int price;
    private String color;
    private String isActive;
    private String isDeleted;
    private java.time.LocalDateTime createDate;
    private java.time.LocalDateTime updateDate;
    private java.time.LocalDateTime deleteDate;


}
