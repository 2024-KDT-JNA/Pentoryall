package com.pentoryall.post.dto;

import lombok.*;

import java.time.LocalDateTime;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class PostSeriesDTO {
    private Long code;
    private Long seriesCode;
    private String seriesTitle;
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
