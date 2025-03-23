package com.gsm._8th.class4.backend.task28.domain.orderItem.data.mapper;

import com.gsm._8th.class4.backend.task28.domain.orderItem.data.OrderItemDto;
import com.gsm._8th.class4.backend.task28.domain.orderItem.entity.OrderItemJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class OrderItemDataMapper {

    public OrderItemDto toOrderItemDto(OrderItemJpaEntity orderItem) {
        return new OrderItemDto(
                orderItem.getItem().getName(),
                orderItem.getItem().getPrice(),
                orderItem.getQuantity()
        );
    }
}