package com.pentoryall.user.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class LikePostDTO {
    private long seriesCode;
    private String seriesTitle;
    private String nickname;
    private String profileImage;
    private long userCode;
    private String postTitle;
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
