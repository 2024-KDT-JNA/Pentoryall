package com.pentoryall.membership.mapper;

import com.pentoryall.membership.dto.MembershipDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MembershipMapper {


    List<MembershipDTO> selectMembership();

    void createMembership(MembershipDTO membershipDTO);

    MembershipDTO selectMembershipByCode(long code);
}
