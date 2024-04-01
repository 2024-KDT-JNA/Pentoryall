package com.pentoryall.common.exception.order;

import com.pentoryall.common.exception.CustomException;

public class InvalidOrderInfoException extends CustomException {

    public InvalidOrderInfoException(String msg) {
        super(msg);
    }
}