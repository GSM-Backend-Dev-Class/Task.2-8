package com.gsm._8th.class4.backend.task28.domain.order.service.impl;

import com.gsm._8th.class4.backend.task28.domain.order.controller.data.OrderDto;
import com.gsm._8th.class4.backend.task28.domain.order.entity.OrderJpaEntity;
import com.gsm._8th.class4.backend.task28.domain.order.exception.OrderNotFoundException;
import com.gsm._8th.class4.backend.task28.domain.order.mapper.OrderDataMapper;
import com.gsm._8th.class4.backend.task28.domain.order.repository.OrderJpaRepository;
import com.gsm._8th.class4.backend.task28.domain.order.service.FindOrderByIdService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindOrderByIdServiceImpl implements FindOrderByIdService {

    private final OrderJpaRepository orderJpaRepository;
    private final OrderDataMapper orderDataMapper;

    @Override
    public OrderDto execute(Long orderId) {
        OrderJpaEntity result = orderJpaRepository.findById(orderId).orElseThrow(OrderNotFoundException::new);
        return orderDataMapper.toSearchOrderDto(result);
    }
}