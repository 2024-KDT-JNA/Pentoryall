package com.pentoryall.common.exception.order;

import com.pentoryall.common.exception.CustomException;

public class OrderFailedException extends CustomException {

    public OrderFailedException(String msg) {
        super(msg);
    }
}