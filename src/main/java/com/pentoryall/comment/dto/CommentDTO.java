package com.pentoryall.comment.dto;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CommentDTO {
    private Long code;
    private Long userCode;
    private Long postCode;
    private Long refCommentCode;
    private String content;
    private char isDeleted;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}
