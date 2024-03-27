package com.pentoryall.series.dto;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.Date;



@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class SeriesDTO {
    private long code;
    private long userCode;
    private String title;
    private String description;
    private String thumbnailImage;
    private char isMembershipOnly;
    private char isDeleted;
    private LocalDateTime createdDate;
    private LocalDateTime updateDate;
}
