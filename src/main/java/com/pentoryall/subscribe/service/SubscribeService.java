package com.pentoryall.subscribe.service;

import com.pentoryall.subscribe.dto.SubscribeDTO;
import com.pentoryall.subscribe.mapper.SubscribeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

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

    public void addSubscribe(SubscribeDTO subscribeDTO) {
    }

    public void cancelSubscribe(SubscribeDTO subscribeDTO) {
    }

    public int getSubscriberCount(long code) {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM subscribe WHERE subscribe_user_code = ?", Integer.class, code);
    }

}