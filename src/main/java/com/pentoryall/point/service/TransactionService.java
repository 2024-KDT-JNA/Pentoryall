package com.pentoryall.point.service;

import com.pentoryall.membership.dto.MembershipDTO;
import com.pentoryall.point.dto.TransactionDTO;
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
    private final UserMapper userMapper;

    @Autowired
    public TransactionService(TransactionMapper transactionMapper, UserMapper userMapper) {
        this.transactionMapper = transactionMapper;
        this.userMapper = userMapper;
    }

    @Transactional
    public void postTransaction(PostDTO post, UserDTO buyer) {
        /* FIXME ::: post의 price가 Long으로 되어 있어서 수정이 필요합니다. */
        int price = Math.toIntExact(post.getPrice());
        buyer.setPoint(buyer.getPoint() - price);
        userMapper.updatePointByUserCode(buyer);

        UserDTO seller = userMapper.getUserInformationByUserCode(post.getUserCode());
        seller.setRevenue(seller.getRevenue() + price);
        userMapper.updateRevenueByUserCode(seller);

        TransactionDTO transaction = new TransactionDTO(post, buyer);
        transactionMapper.save(transaction);
    }

    @Transactional
    public void membershipTransaction(MembershipDTO membership, UserDTO buyer) {
        int price = membership.getPrice();
        buyer.setPoint(buyer.getPoint() - price);
        userMapper.updatePointByUserCode(buyer);

        UserDTO seller = userMapper.getUserInformationByUserCode(membership.getUserCode());
        seller.setRevenue(seller.getRevenue() + price);
        userMapper.updateRevenueByUserCode(seller);

        TransactionDTO transaction = new TransactionDTO(membership, buyer);
        transactionMapper.save(transaction);
    }
}

