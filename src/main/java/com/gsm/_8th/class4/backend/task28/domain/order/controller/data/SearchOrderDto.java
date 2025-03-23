package com.gsm._8th.class4.backend.task28.domain.order.controller.data;

import com.gsm._8th.class4.backend.task28.domain.orderItem.data.OrderItemDto;
import com.gsm._8th.class4.backend.task28.domain.user.data.UserDto;

import java.util.List;
import java.util.UUID;

public record SearchOrderDto(
        Long id,
        UUID uuid,
        UserDto user,
        int price,
        String address,
        List<OrderItemDto> orderItems
) {
}