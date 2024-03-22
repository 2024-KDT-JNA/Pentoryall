package com.pentoryall.membership.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class MembershipDTO {
    private int code;
    private int userCode;
    private String name;
    private String introduction;
    private int price;
    private String color;
    private boolean isActive;
    private boolean isDeleted;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private LocalDateTime deleteDate;

    public MembershipDTO() {

    }

}