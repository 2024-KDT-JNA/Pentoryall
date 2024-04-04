package com.pentoryall.admin.DTO;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Getter
@Setter
@ToString
@Component
public class GenreManageDTO {

    private long code;
    private long refGenreCode;
    private String name;

}
