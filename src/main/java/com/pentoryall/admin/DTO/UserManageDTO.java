package com.pentoryall.admin.DTO;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
public class UserManageDTO {

    private long code;
    private String email;
    private String userId;
    private String nickName;
    private String name;
    private Date birth;
    private String state;

}
