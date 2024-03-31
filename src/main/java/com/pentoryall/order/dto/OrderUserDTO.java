package com.pentoryall.order.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class OrderUserDTO {

    private Long code;
    private String userId;
    private String name;
    private String email;
    private int point;
}
