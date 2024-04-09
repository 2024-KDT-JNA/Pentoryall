package com.pentoryall.admin.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class ModifyPostDTO {

    private long code;

    private String userId;

    private String nickName;

    private String title;

    private String confirmContent;

    private int price;

    private Date updateDate;

    private String isConfirmed;

}
