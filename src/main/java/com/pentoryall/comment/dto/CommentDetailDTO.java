package com.pentoryall.comment.dto;

import com.pentoryall.user.dto.UserDTO;
import lombok.*;

import java.time.LocalDateTime;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CommentDetailDTO {
    private Long code;
    private UserDTO user;
    private Long postCode;
    private Long refCommentCode;
    private String content;
    private char isDeleted;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}
