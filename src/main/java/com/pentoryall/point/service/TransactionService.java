package com.pentoryall.point.service;

import com.pentoryall.point.dto.TransactionDTO;
import com.pentoryall.point.enums.TransactionType;
import com.pentoryall.point.mapper.TransactionMapper;
import com.pentoryall.user.dto.UserDTO;
import com.pentoryall.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionMapper pointTransactionMapper;
    private final UserMapper userMapper;


    public void purchase(TransactionDTO transaction, UserDTO sessionUser, UserDTO sellerUser) {
        // 작품 구매
        if (transaction.getType() == TransactionType.POST) {
            pointTransactionMapper.save(transaction);
        }
        // UserDTO buyer = new UserDTO();

        // UserDTO seller = new UserDTO();
    }
}


// 거래 테이블에 내역 넣기?
// 구매자 포인트 차감..
// 판매자 포인트 증가..

// if(isAlreadyPurchaseByMembershipCode()){
//
// }
