package com.pentoryall.point.service;

import com.pentoryall.membership.dto.MembershipDTO;
import com.pentoryall.membership.mapper.MembershipMapper;
import com.pentoryall.point.dto.TransactionDTO;
import com.pentoryall.point.enums.TransactionType;
import com.pentoryall.point.mapper.TransactionMapper;
import com.pentoryall.post.dto.PostDTO;
import com.pentoryall.user.dto.UserDTO;
import com.pentoryall.user.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class TransactionService {

    private final TransactionMapper transactionMapper;

    private final MembershipMapper membershipMapper;

    private final UserMapper userMapper;

    @Autowired
    public TransactionService(TransactionMapper transactionMapper, MembershipMapper membershipMapper, UserMapper userMapper) {
        this.transactionMapper = transactionMapper;
        this.membershipMapper = membershipMapper;
        this.userMapper = userMapper;
    }

    public Long existsTransactionCode(Long userCode, Long productCode, TransactionType type) {
        TransactionDTO transaction = new TransactionDTO(userCode, productCode, type);
        return transactionMapper.existsTransactionCode(transaction);
    }

    @Transactional
    public void postTransaction(PostDTO post, UserDTO buyer) {
        int price = post.getPrice();
        buyer.setPoint(buyer.getPoint() - price);
        userMapper.updatePointByUserCode(buyer);

        UserDTO seller = userMapper.getUserInformationByUserCode(post.getUserCode());
        seller.setRevenue(seller.getRevenue() + price);
        userMapper.updateRevenueByUserCode(seller);

        TransactionDTO transaction = new TransactionDTO(buyer, post);
        transactionMapper.save(transaction);
    }

    @Transactional
    public void membershipTransaction(MembershipDTO membership, UserDTO buyer) {
        // 1. 이미 가입했는가?
        // 2. 가입 후 매달 자동 연장 (한달 단위, 30일)
        // 2.1 해지 신청 시 membership_join 테이블의 end_date가 갱신
        // 2.2 자동 연장 시점에 `보유 포인트 < 멤버십 가격` 시 end_date 갱신 후 해지 처리
        // 2.3 보유 포인트가 충분하다면 transaction 테이블에 포인트 사용 내역이 insert 됨

        int price = membership.getPrice();
        buyer.setPoint(buyer.getPoint() - price);
        userMapper.updatePointByUserCode(buyer);

        UserDTO seller = userMapper.getUserInformationByUserCode(membership.getUserCode());
        seller.setRevenue(seller.getRevenue() + price);
        userMapper.updateRevenueByUserCode(seller);

        TransactionDTO transaction = new TransactionDTO(buyer, membership);
        transactionMapper.save(transaction);
    }

}

