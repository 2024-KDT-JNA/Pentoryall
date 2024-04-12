package com.pentoryall.subscribe.service;

import com.pentoryall.subscribe.dto.SubscribeDTO;
import com.pentoryall.subscribe.mapper.SubscribeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SubscribeService {

    private final SubscribeMapper subscribeMapper;
    @Autowired
    private JdbcTemplate jdbcTemplate;


    public SubscribeService(SubscribeMapper subscribeMapper) {
        this.subscribeMapper = subscribeMapper;
    }


    public List<SubscribeDTO> selectAllSubscribers(long code) {
        return subscribeMapper.getAllSubscribers(code);
    }


    public List<SubscribeDTO> selectAllSubscribeStory(long code) {
        return subscribeMapper.getAllSubscribeStory(code);
    }


    public void updateSubscriberVisible(long userCode, char isSubscriberVisible) {
        List<SubscribeDTO> subscribers = subscribeMapper.selectSubscribersByUserCode(userCode);
        if (subscribers != null && !subscribers.isEmpty()) {
            for (SubscribeDTO subscribeDTO : subscribers) {
                subscribeDTO.setIsSubscriberVisible(isSubscriberVisible);
                subscribeMapper.updateSubscriberStatus(subscribeDTO);
            }
        }
    }

    @Transactional
    public void addSubscriber(SubscribeDTO subscribeDTO) {
        subscribeMapper.addSubscriber(subscribeDTO);
    }

    @Transactional
    public void cancelSubscribe(SubscribeDTO subscribeDTO) {
        subscribeMapper.cancelSubscriber(subscribeDTO);
    }
}