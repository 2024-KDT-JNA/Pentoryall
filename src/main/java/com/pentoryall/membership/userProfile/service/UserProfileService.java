package com.pentoryall.membership.userProfile.service;

import com.pentoryall.membership.dto.MembershipDTO;
import com.pentoryall.membership.userProfile.mapper.UserProfileMapper;
import com.pentoryall.subscribe.dto.SubscribeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserProfileService {
    private final UserProfileMapper userProfileMapper;

    @Autowired
    public UserProfileService(UserProfileMapper userProfileMapper) {
        this.userProfileMapper = userProfileMapper;
    }

    public MembershipDTO selectMembershipUserProfileBySubscribeUserCode(long subscribeUserCode) {
        return userProfileMapper.selectMembershipByUserProfile(subscribeUserCode);
    }

    public List<SubscribeDTO> selectUserSubscriberList(long code) {
        return userProfileMapper.getUserSubscriberList(code);
    }
}
