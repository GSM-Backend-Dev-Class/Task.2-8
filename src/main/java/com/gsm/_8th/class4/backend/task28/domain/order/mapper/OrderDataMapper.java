package com.gsm._8th.class4.backend.task28.domain.order.mapper;

import com.gsm._8th.class4.backend.task28.domain.order.controller.data.SearchOrderDto;
import com.gsm._8th.class4.backend.task28.domain.order.entity.OrderJpaEntity;
import com.gsm._8th.class4.backend.task28.domain.orderItem.data.mapper.OrderItemDataMapper;
import com.gsm._8th.class4.backend.task28.domain.user.data.mapper.UserDataMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderDataMapper {

    private final UserDataMapper userDataMapper;
    private final OrderItemDataMapper orderItemDataMapper;

    public SearchOrderDto toSearchOrderDto(OrderJpaEntity order) {
        return new SearchOrderDto(
                order.getId(),
                order.getUuid(),
                userDataMapper.toUserDto(order.getUser()),
                order.getPrice(),
                order.getAddress(),
                order.getOrderItems().stream()
                        .map(orderItemDataMapper::toOrderItemDto)
                        .toList()
        );
    }
}