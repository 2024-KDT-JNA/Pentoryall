package com.pentoryall.user.mapper;

import com.pentoryall.user.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    UserDTO findByUserId(String userId);

    String selectUserById(String userId);

    int insertUser(UserDTO user);

    int deleteUser(UserDTO user);
}
