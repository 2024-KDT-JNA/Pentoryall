package com.pentoryall.point.service;

import com.pentoryall.point.dto.TransactionDTO;
import com.pentoryall.point.enums.TransactionType;
import com.pentoryall.point.mapper.TransactionMapper;
import com.pentoryall.post.dto.PostDTO;
import com.pentoryall.user.dto.UserDTO;
import com.pentoryall.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

        TransactionDTO transaction = new TransactionDTO();
        transaction.setPostCode(post.getCode());
        transaction.setUserCode(buyer.getCode());
        transaction.setSellerUserCode(seller.getCode());
        transaction.setType(TransactionType.POST);
        transaction.setPoint(price);
        transactionMapper.save(transaction);
    }
}

