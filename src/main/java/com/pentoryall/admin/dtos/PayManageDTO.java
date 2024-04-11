package com.pentoryall.admin.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalTime;

@Getter
@Setter
@ToString
public class PayManageDTO {

    private long code;
    private int requestAmount;
    private String cause;
    private String state;
    private long userCode;
    private String bankCode;
    private String accountNumber;
    private LocalTime createDate;
}
