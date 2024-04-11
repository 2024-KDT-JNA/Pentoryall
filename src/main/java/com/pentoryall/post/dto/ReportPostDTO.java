package com.pentoryall.post.dto;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ReportPostDTO {
    private Long code;
    private int reason;
    private Long userCode;
    private Long postCode;
    private Long commentCode;
    private String type;
    private LocalDateTime reportDate;

}
