package com.gsm._8th.class4.backend.task28.domain.order.repository.custom;

import com.gsm._8th.class4.backend.task28.domain.order.entity.OrderJpaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface OrderRepositoryCustom {
    Page<OrderJpaEntity> searchOrders(Long userId, Integer minPrice, Integer maxPrice, String address, String itemName, Pageable pageable);
}