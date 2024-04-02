package com.pentoryall.subscribe.mapper;

import com.pentoryall.subscribe.dto.SubscribeDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SubscribeMapper {


//    void addSubscription(SubscribeDTO subscribeDTO);
//
//    void cancelSubscription(SubscriptionCode subscriptionCode);

    List<SubscribeDTO> getAllSubscribers(long code);


}
