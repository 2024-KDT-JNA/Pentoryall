package com.pentoryall.user.mapper;

import com.pentoryall.PentoryallApplication;
import com.pentoryall.user.dto.UserDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@ContextConfiguration(classes = { PentoryallApplication.class })
class UserMapperTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    @DisplayName("회원 포인트 충전 - 성공")
    @Transactional
    public void updatePointByUserCodeSuccess() {

        // given
        UserDTO user = userMapper.getUserInformationByUserCode(1L);
        System.out.println("충전 전 회원의 포인트 : " + user.getPoint());

        int beforePoint = user.getPoint();
        int addedPoint = beforePoint + 50000;

        // when
        user.setPoint(addedPoint);
        userMapper.updatePointByUserCode(user);

        // then
        user = userMapper.getUserInformationByUserCode(user.getCode());
        System.out.println("충전 후 회원의 포인트 : " + user.getPoint());

        Assertions.assertEquals(user.getPoint(), addedPoint);
    }
}