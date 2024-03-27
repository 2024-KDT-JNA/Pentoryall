package com.pentoryall.user.service;


import com.pentoryall.common.exception.user.MemberModifyException;
import com.pentoryall.common.exception.user.MemberRegistException;
import com.pentoryall.common.exception.user.MemberRemoveException;
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

    @Transactional
    public void removeUser(UserDTO user) throws MemberRemoveException {
        int result = userMapper.deleteUser(user);

        if (!(result > 0)) {
            throw new MemberRemoveException("회원 탈퇴에 실패하였습니다.");
        }

//    public UserDTO getUserInformationByPostCode(long userCode) {
//        return userMapper.getUserInformationByPostCode(userCode);
//    }
    }

    public void modifyUser(UserDTO modifyUser) throws MemberModifyException {
        int result = userMapper.updateUser(modifyUser);

        if (!(result > 0)) throw new MemberModifyException("회원 정보 수정에 실패하였습니다.");
    }
}
