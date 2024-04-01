package com.pentoryall.admin.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.service.annotation.GetExchange;

import java.util.Date;

@Getter
@Setter
@ToString
public class PostReportDTO {

    private long code;

    private long userCode;

    private String userId;

    private String nickName;

    private String title;

    private String type;

    private Date reportDate;
}
