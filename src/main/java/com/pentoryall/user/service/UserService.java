package com.pentoryall.user.service;

import com.pentoryall.user.dto.UserDTO;
import com.pentoryall.user.mapper.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    UserMapper userMapper;

    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public UserDTO getUserInformationByPostCode(long userCode) {
        return userMapper.getUserInformationByPostCode(userCode);
    }
}
