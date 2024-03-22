package com.pentoryall.user.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LikeDTO {
    private long code;
    private long userCode;
    private long postCode;
}
