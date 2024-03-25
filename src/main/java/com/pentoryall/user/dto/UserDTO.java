package com.pentoryall.user.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pentoryall.user.model.UserRole;
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
    private String role;
    private String state;
    private char isCertificated;
    private char isSubscriberVisible;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private LocalDateTime deleteDate;
    private LocalDateTime suspensionEndDate;
    private UserRole userRole;

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String roleName = "";
        if (userRole != null) roleName = userRole.name();
        return Arrays.asList(new SimpleGrantedAuthority(roleName));
    }

    @Override
    public String getUsername() {
        return null;
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
