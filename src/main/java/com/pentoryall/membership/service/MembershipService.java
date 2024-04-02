package com.pentoryall.membership.service;

import com.pentoryall.membership.dto.MembershipDTO;
import com.pentoryall.membership.mapper.MembershipMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MembershipService {
    private final MembershipMapper membershipMapper;

    public MembershipService(MembershipMapper membershipMapper) {
        this.membershipMapper = membershipMapper;
    }

    @Transactional
    public MembershipDTO createMembership(MembershipDTO membershipDTO) {
        membershipMapper.createMembership(membershipDTO);
        return membershipDTO;
    }

    public MembershipDTO selectMembershipByUserCode(long userCode) {
        return membershipMapper.selectMembershipByUserCode(userCode);
    }

    @Transactional
    public void modifyMembership(MembershipDTO membershipDTO) {
        membershipMapper.modifyMembership(membershipDTO);
    }

    public void updateIsDeleted(long code, char Y) {
        membershipMapper.updateIsDeleted(code, Y);
    }


    public MembershipDTO selectMembershipByCode(long code) {
        return membershipMapper.selectMembershipByUserCode(code);
    }
}

//    public List<MembershipDTO> getAllMemberships() {
//        return membershipMapper.getAllMemberships();
//    }
//

//
//

//


//
//    public MembershipDTO selectMembershipByUserCode(long code) {
//        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>> service!!!!!!!!!! code ="+code);
//
//    }




