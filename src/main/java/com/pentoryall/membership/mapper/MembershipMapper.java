package com.pentoryall.membership.mapper;

import com.pentoryall.membership.dto.MembershipDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MembershipMapper {

    void createMembership(MembershipDTO membershipDTO);

    MembershipDTO selectMembershipByUserCode(long userCode);

    void modifyMembership(MembershipDTO membershipDTO);


    void updateIsDeleted(long code, char Y);

}

//
//
//    void updateIsDeleted(long code);
//
//    void updateMembership(MembershipDTO membershipDTO);
//
//    MembershipDTO getMembershipByCode(long code);
//
//    MembershipDTO selectMembershipByUserCode(long code);
//}
