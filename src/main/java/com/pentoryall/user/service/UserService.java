package com.pentoryall.user.service;


import com.pentoryall.common.exception.user.MemberModifyException;
import com.pentoryall.common.exception.user.MemberRegistException;
import com.pentoryall.common.exception.user.MemberRemoveException;
import com.pentoryall.user.dto.UserDTO;
import com.pentoryall.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

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
    }

    public UserDTO getUserInformationByUserCode(long userCode) {
        return userMapper.getUserInformationByUserCode(userCode);
    }

    public void modifyUser(UserDTO modifyUser) throws MemberModifyException {
        System.out.println(modifyUser);
        if (!modifyUser.getPassword().equals("")) {
            // 비밀번호 인코딩(암호화)
            String encodedPassword = passwordEncoder.encode(modifyUser.getPassword());
            modifyUser.setPassword(encodedPassword);
        }
        char isSubscriberVisible = modifyUser.getIsSubscriberVisible();  // <--- 구독 개여부 가져오기
        modifyUser.setIsSubscriberVisible(isSubscriberVisible);  //<------------ 구독 공개여부 다시 설정하여 반영 추가 (승재)
        int result = userMapper.updateUser(modifyUser);

        if (!(result > 0)) throw new MemberModifyException("회원 정보 수정에 실패하였습니다.");
    }

    public String getPwd(long code) {

        String pwd = userMapper.getPwd(code);

        return pwd;
    }

    public boolean selectUserByNickname(String nickname) {
        String result = userMapper.selectUserByNickname(nickname);

        return result != null;
    }

    public List<UserDTO> getUserListByWord(String word) {
        return userMapper.getUserListByWord(word);
    }

    public boolean checkEmailExists(String email) {
        String result = userMapper.checkEmailExists(email);

        return result != null;
    }

    public void changeFindPw(String tempPw, String email) {
        if (!tempPw.equals("")) {
            // 비밀번호 인코딩(암호화)
            String encodedPassword = passwordEncoder.encode(tempPw);
            /* 데이터 보내기 */
            userMapper.changeFindPw(encodedPassword, email);
        }

    }

    public boolean selectUserByEmail(String email) {
        String result = userMapper.selectUserByEmail(email);

        return result != null;
    }

//    public boolean isPasswordCorrect(String userId, String enteredPassword) {
//        // 사용자 정보를 DB에서 가져옵니다.
//        User user = userRepository.findByUserId(userId);
//
//        if (user == null) {
//            return false; // 사용자가 존재하지 않으면 비밀번호 일치 여부를 false로 반환합니다.
//        }
//
//        // DB에서 가져온 사용자의 비밀번호를 가져옵니다.
//        String storedPassword = user.getPassword();
//
//        // 입력된 비밀번호와 DB에서 가져온 비밀번호를 비교하여 일치 여부를 확인합니다.
//        return passwordEncoder.matches(enteredPassword, storedPassword);
//    }
}