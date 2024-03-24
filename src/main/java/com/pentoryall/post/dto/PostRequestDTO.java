package com.pentoryall.post.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class PostRequestDTO {
    private String title;
    private String content;
}
