package com.pentoryall.post.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class PostDTO {
    private Long code;
    private Long seriesCode;
    private Long userCode;
    private String title;
    private String content;
    private String confirmContent;
    private String thumbnailImage;
    private int views;
    private int price;
    private char isPaid;
    private char isAdult;
    private char isPublic;
    private char isDeleted;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;


}
