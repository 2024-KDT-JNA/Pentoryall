package com.pentoryall.admin.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class CommentReportDTO {

    private long code;

    private String userId;

    private String nickName;

    private String state;

    private String type;

    private Date reportDate;

    private String content;

    private Date createDate;

    private String title;

    private long userCode;

    private long postCode;

    private long commentCode;

    private String name;




}
