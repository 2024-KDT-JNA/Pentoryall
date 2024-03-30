package com.pentoryall.genreOfArt.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class GenreOfArtDTO {
    private long code;
    private long genreCode;
    private long seriesCode;
    private long postCode;
    private String kind;
}
