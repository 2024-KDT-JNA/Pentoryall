package com.pentoryall.subscribe.service;

import com.pentoryall.subscribe.dto.SubscribeDTO;
import com.pentoryall.subscribe.mapper.SubscribeMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscribeService {

    private final SubscribeMapper subscribeMapper;

    public SubscribeService(SubscribeMapper subscribeMapper) {
        this.subscribeMapper = subscribeMapper;
    }

//    public void addSubscribe(SubscribeDTO subscribeDTO) {
//        // 구독 추가
//        subscribeMapper.addSubscription(subscribeDTO);
//    }
//
//    public void cancelSubscribe(SubscribeDTO subscribeDTO) {
//        // 구독 취소
//        subscribeMapper.cancelSubscription(subscribeDTO);
//    }


    public List<SubscribeDTO> selectAllSubscribers(long code) {
        return subscribeMapper.getAllSubscribers(code);
    }

    public void addSubscribe(SubscribeDTO subscribeDTO) {
    }

    public void cancelSubscribe(SubscribeDTO subscribeDTO) {
    }
}