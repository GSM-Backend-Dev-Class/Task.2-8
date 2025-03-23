package com.gsm._8th.class4.backend.task28.global.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    ORDER_NOT_FOUND("Order not found", HttpStatus.NOT_FOUND);

    private final String message;
    private final HttpStatus httpStatus;
}