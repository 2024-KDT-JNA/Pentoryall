package com.pentoryall.user.service;

import com.pentoryall.common.exception.user.MemberRegistException;
import com.pentoryall.user.dto.UserDTO;
import com.pentoryall.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;

    public boolean selectUserById(String userId) {

        String result = userMapper.selectUserById(userId);

        return result != null;
    }

    @Transactional
    public void registUser(UserDTO user) throws MemberRegistException {

        int result1 = userMapper.insertUser(user);

        if (!(result1 > 0)) throw new MemberRegistException("회원 가입에 실패하였습니다.");
    }
}
