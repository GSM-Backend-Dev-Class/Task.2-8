package com.gsm._8th.class4.backend.task28.domain.order.exception;

import com.gsm._8th.class4.backend.task28.global.error.ErrorCode;
import com.gsm._8th.class4.backend.task28.global.error.exception.GlobalException;

public class OrderNotFoundException extends GlobalException {
    public OrderNotFoundException() {
        super(ErrorCode.ORDER_NOT_FOUND);
    }
}