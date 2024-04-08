package com.pentoryall.story.dto;

import com.pentoryall.user.dto.UserDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor

@ToString
public class StoryUserDTO {

    private long code;
    private String userId;
    private String nickname;
    private String profileImage;
    private String introduction;
    private int revenue;
    private int point;
    private char isSubscriberVisible;

    public StoryUserDTO(UserDTO user) {
        this.code = user.getCode();
        this.userId = user.getUserId();
        this.nickname = user.getNickname();
        this.profileImage = user.getProfileImage();
        this.introduction = user.getIntroduction();
        this.revenue = user.getRevenue();
        this.point = user.getPoint();
        this.isSubscriberVisible = user.getIsSubscriberVisible();
    }
}
