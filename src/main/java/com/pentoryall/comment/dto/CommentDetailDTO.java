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
    private long code;
    private long postCode;
    private long refCommentCode;
    private String content;
    private char isDeleted;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private UserDTO user;
}
