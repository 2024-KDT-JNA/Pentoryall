package com.pentoryall.point.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ImpOrderDTO {

    Long code;
    Long orderCode;
    String impUid;
    LocalDateTime createDate;
}
