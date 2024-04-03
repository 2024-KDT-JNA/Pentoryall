package com.pentoryall.subscribe.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class SubscribeDTO {
    private long code;
    private long userCode;
    private long subscribeUserCode;
    private LocalDate subscribedDate;
    private String nickname;

}