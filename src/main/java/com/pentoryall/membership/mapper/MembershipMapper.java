package com.pentoryall.membership.mapper;

import com.pentoryall.membership.dto.MembershipDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MembershipMapper {

    void createMembership(MembershipDTO membershipDTO);

    List<MembershipDTO> getAllMemberships();

    void modifyMembership(MembershipDTO membershipDTO);

    void deleteMembership(long code);
}


