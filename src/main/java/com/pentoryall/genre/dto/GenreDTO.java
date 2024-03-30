package com.pentoryall.genre.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class GenreDTO {
    private long code;
    private long refGenreCode;
    private String name;
}
