package com.pentoryall.post.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ValidatePostDTO {

    private Long userCode;
    private Long seriesCode;
    private Long postCode;
    private int price;
    private char isPaid;
    private char isAdult;
    private char isPublic;
    private char isMembershipOnly;
}
