package com.pentoryall.membership.service;

import com.pentoryall.membership.dto.MembershipDTO;
import com.pentoryall.membership.mapper.MembershipMapper;
import org.springframework.stereotype.Service;

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

}