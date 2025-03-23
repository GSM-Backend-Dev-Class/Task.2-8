package com.gsm._8th.class4.backend.task28.domain.order.service.impl;

import com.gsm._8th.class4.backend.task28.domain.order.controller.data.SearchOrderResponse;
import com.gsm._8th.class4.backend.task28.domain.order.entity.OrderJpaEntity;
import com.gsm._8th.class4.backend.task28.domain.order.mapper.OrderDataMapper;
import com.gsm._8th.class4.backend.task28.domain.order.repository.OrderJpaRepository;
import com.gsm._8th.class4.backend.task28.domain.order.service.SearchOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SearchOrderServiceImpl implements SearchOrderService {

    private final OrderJpaRepository orderJpaRepository;
    private final OrderDataMapper orderDataMapper;

    @Override
    public SearchOrderResponse execute(Long userId, int minPrice, int maxPrice, String address, String itemName, int page, int size) {
        Page<OrderJpaEntity> result = orderJpaRepository.searchOrders(userId, minPrice, maxPrice, address, itemName, PageRequest.of(page, size));
        return new SearchOrderResponse(
                result.getTotalPages(),
                result.getTotalElements(),
                result.getContent().stream().map(
                        orderDataMapper::toSearchOrderDto
                ).collect(Collectors.toList()));
    }
}