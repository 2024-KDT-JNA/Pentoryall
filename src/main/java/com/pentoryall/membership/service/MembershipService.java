package com.pentoryall.membership.service;

import com.pentoryall.membership.dto.MembershipDTO;
import com.pentoryall.membership.dto.MembershipJoinDTO;
import com.pentoryall.membership.mapper.MembershipMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MembershipService {

    private final MembershipMapper membershipMapper;


    public MembershipService(MembershipMapper membershipMapper) {
        this.membershipMapper = membershipMapper;
    }

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


    //-------------------------------- membershipJoin service -----------------------------------
    public List<MembershipJoinDTO> selectAllMembershipJoinList(long code) {
        return membershipMapper.selectMembershipJoinList(code);
    }


    public List<MembershipJoinDTO> selectAllJoinMemberList(long code) {
        return membershipMapper.selectJoinMemberList(code);
    }

    public Long existsMembershipJoinCode(Long membershipCode, Long userCode) {
        MembershipJoinDTO membershipJoin = new MembershipJoinDTO();
        membershipJoin.setUserCode(userCode);
        membershipJoin.setMembershipCode(membershipCode);
        return membershipMapper.existsMembershipJoinCode(membershipJoin);
    }
}





