package com.gsm._8th.class4.backend.task28.global.error.exception;

import com.gsm._8th.class4.backend.task28.global.error.ErrorCode;
import lombok.Getter;

@Getter
public class GlobalException extends RuntimeException {

    private final ErrorCode errorCode;

    public GlobalException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}