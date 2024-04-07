package com.pentoryall.membership.mapper;

import com.pentoryall.membership.dto.MembershipDTO;
import com.pentoryall.membership.dto.MembershipJoinDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MembershipMapper {

    void createMembership(MembershipDTO membershipDTO);

    MembershipDTO selectMembershipByUserCode(long userCode);

    void modifyMembership(MembershipDTO membershipDTO);


    void updateIsDeleted(long code, char Y);


    //-------------------------- membership Join Mapper --------------------------
    List<MembershipJoinDTO> selectMembershipJoinList(long code);

    List<MembershipJoinDTO> selectJoinMemberList(long code);


    MembershipDTO selectMembershipByUserProfile(long subscribeUserCode);
}




