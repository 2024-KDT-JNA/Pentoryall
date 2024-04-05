package com.pentoryall.genreOfArt.controller;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class GenreRequest {
    private Long genre;
    private Long lowerGenre;
}
