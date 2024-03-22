package com.pentoryall.user.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class UserDTO {
    private long code;
    private String email;
    private String userId;
    private String password;
    private String nickname;
    private String name;
    private LocalDate birth;
    private String profileImage;
    private String introduction;
    private int revenue;
    private int point;
    private String role;
    private String state;
    private char isCertificated;
    private char isSubscriberVisible;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private LocalDateTime deleteDate;
    private LocalDateTime suspensionEndDate;

}
