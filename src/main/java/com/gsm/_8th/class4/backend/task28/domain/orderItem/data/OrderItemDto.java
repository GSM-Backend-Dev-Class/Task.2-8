package com.gsm._8th.class4.backend.task28.domain.orderItem.data;

public record OrderItemDto(
        String orderItemName,
        int orderItemPrice,
        int orderItemQuantity
) {
}