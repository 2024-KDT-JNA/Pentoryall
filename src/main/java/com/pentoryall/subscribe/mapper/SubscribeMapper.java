package com.pentoryall.subscribe.mapper;

import com.pentoryall.subscribe.dto.SubscribeDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SubscribeMapper {

    List<SubscribeDTO> getAllSubscribers(long code);


    void addSubscriber(SubscribeDTO subscribeDTO);

    void cancelSubscriber(SubscribeDTO subscribeDTO);

    List<SubscribeDTO> getAllSubscribeStory(long code);

    List<SubscribeDTO> selectSubscribersByUserCode(long userCode);

    void updateSubscriberStatus(SubscribeDTO subscribeDTO);
}


