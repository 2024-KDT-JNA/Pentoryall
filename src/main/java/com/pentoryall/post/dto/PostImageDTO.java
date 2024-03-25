package com.pentoryall.post.dto;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class PostImageDTO {
    private long code;
    private long postCode;
    private String filename;
    private String ext;
    private LocalDateTime CreateDate;
}
