package com.pentoryall.user.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pentoryall.user.model.Role;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;

@Getter
@Setter
@ToString
public class UserDTO implements UserDetails {
    private long code;
    private String email;
    private String userId;
    private String password;
    private String nickname;
    private String name;
    private LocalDate birth;
    private String profileImage;
    private String introduction;
    private int revenue;
    private int point;
    //    private String role;
    private String state;
    private char isCertificated;
    private char isSubscriberVisible;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private LocalDateTime deleteDate;
    private LocalDateTime suspensionEndDate;
    /* model파일 안에 Role파일 가져옴
     *  아마도 자동 매핑이라 db하고 이름 똑같이 해야하는듯 */
    private Role role;

    /* post 필요없음 */
    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String roleName = "";
        if (role != null) roleName = role.name();
        return Arrays.asList(new SimpleGrantedAuthority(roleName));
    }

    /* 스프링시큐리티에서 user를 구분하는 이름 = (id의 개념)
     * 승재씨 바 꾸 지 마 세 요 건 들 지 마 !!!!!!!!!! */
    @Override
    public String getUsername() {
        return userId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}