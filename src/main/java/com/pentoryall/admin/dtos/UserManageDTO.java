package com.pentoryall.admin.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserManageDTO {

    private long code;
    private String email;
    private String userId;
    private String nickName;
    private String name;
    private Date birth;
    private String state;

}
