package com.gsm._8th.class4.backend.task28.domain.order.service;

import com.gsm._8th.class4.backend.task28.domain.order.controller.data.OrderDto;

public interface FindOrderByIdService {
    OrderDto execute(Long orderId);
}