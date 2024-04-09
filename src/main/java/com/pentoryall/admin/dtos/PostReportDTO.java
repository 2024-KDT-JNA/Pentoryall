package com.pentoryall.admin.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class PostReportDTO {

    private long postCode;

    private long code;

    private long userCode;

    private String userId;

    private String nickName;

    private String title;

    private String type;

    private long confirmContent;

    private Date reportDate;
}
