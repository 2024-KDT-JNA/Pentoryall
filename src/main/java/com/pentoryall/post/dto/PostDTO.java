package com.pentoryall.post.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;


import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class PostDTO {
    private long code;
    private long seriesCode;
    private long userCode;
    private String title;
    private String content;
    private String confirmContent;
    private String thumbnailImage;
    private long views;
    private long price;
    private char isPaid;
    private char isAdult;
    private char isPublic;
    private char isDeleted;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}
