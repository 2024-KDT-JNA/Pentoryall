package com.pentoryall.subscribe.mapper;

import com.pentoryall.membership.dto.MembershipDTO;
import com.pentoryall.subscribe.dto.SubscribeDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SubscribeMapper {


    void addSubscribe(SubscribeDTO subscribeDTO);

    void cancelSubscribe(SubscribeDTO subscribeDTO);
}
