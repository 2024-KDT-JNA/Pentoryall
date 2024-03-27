package com.pentoryall.subscribe.service;

import com.pentoryall.subscribe.dto.SubscribeDTO;
import com.pentoryall.subscribe.mapper.SubscribeMapper;
import org.springframework.stereotype.Service;

@Service
public class SubscribeService {
    private final SubscribeMapper subscribeMapper;
    public SubscribeService (SubscribeMapper subscribeMapper){
        this.subscribeMapper = subscribeMapper;
    }
    public void addSubscribe(SubscribeDTO subscribeDTO){
        subscribeMapper.addSubscribe(subscribeDTO);
    }
    public void cancelSubscribe(SubscribeDTO subscribeDTO){
        subscribeMapper.cancelSubscribe(subscribeDTO);

    }

}
