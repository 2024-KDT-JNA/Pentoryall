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
    public int getSubscriberCount(long code) {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM subscribe WHERE subscribe_user_code = ?", Integer.class, code);
    }

    public List<SubscribeDTO> selectAllSubscribeStory(long code) {
        return subscribeMapper.getAllSubscribeStory(code);
    }
    public int getSubscribeStoryCount(long code) {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM subscribe WHERE user_code = ?", Integer.class, code);
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