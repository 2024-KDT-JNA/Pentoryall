package com.pentoryall.membership.service;

import com.pentoryall.membership.dto.MembershipDTO;
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

    @Transactional
    public void createMembership(MembershipDTO membershipDTO) {
        membershipMapper.createMembership(membershipDTO);
    }

    public List<MembershipDTO> getAllMemberships() {
        return membershipMapper.getAllMemberships();
    }

    @Transactional
    public void modifyMembership(MembershipDTO membershipDTO) {
        membershipMapper.modifyMembership(membershipDTO);


    }


    public void updateIsDeleted(Long code, char y) {
        // updateIsDelete 메서드를 호출하여 멤버십 정보를 업데이트합니다.
        membershipMapper.updateIsDelete(code);

        // 업데이트된 멤버십 정보를 확인하여 삭제되지 않은 경우에만 처리합니다.
        MembershipDTO membershipDTO = membershipMapper.getMembershipByCode(code);
        if (membershipDTO != null && membershipDTO.getIsDeleted() != 'Y') {
            // 멤버십을 삭제 상태로 설정하고 업데이트합니다.
            membershipDTO.setIsDeleted(y);
            membershipMapper.updateMembership(membershipDTO);
        }
    }


    public MembershipDTO getMembershipByCode(long code) {
        return membershipMapper.getMembershipByCode(code);
    }

    public MembershipDTO selectMembershipByUserCode(long code) {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>> service!!!!!!!!!! code ="+code);
        return membershipMapper.selectMembershipByUserCode(code);
    }
}



