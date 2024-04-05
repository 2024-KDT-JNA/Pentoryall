package com.pentoryall.user.mapper;

import com.pentoryall.user.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    List<UserDTO> getUserListByWord(String word);

    UserDTO findByUserId(String userId);

    String selectUserById(String userId);


    UserDTO getUserInformationByUserCode(long userCode);

    String getPwd(long code);

    String selectUserByNickname(String nickname);

    String getLikedPostCount();

    int insertUser(UserDTO user);

    int updateUser(UserDTO modifyUser);

    int deleteUser(UserDTO user);


    void updatePointByUserCode(UserDTO user);

    void updateRevenueByUserCode(UserDTO sellerUser);
}