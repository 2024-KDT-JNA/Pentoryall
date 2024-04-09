package com.pentoryall.membership.userProfile.mapper;

import com.pentoryall.membership.dto.MembershipDTO;
import com.pentoryall.subscribe.dto.SubscribeDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserProfileMapper {
    MembershipDTO selectMembershipByUserId(String userId);

    List<SubscribeDTO> getUserSubscriberListByUserId(String userId);
}
