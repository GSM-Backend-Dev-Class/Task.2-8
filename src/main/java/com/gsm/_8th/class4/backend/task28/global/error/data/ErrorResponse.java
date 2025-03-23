package com.gsm._8th.class4.backend.task28.global.error.data;

import org.springframework.http.HttpStatus;

public record ErrorResponse(String message, int httpStatus) {
}