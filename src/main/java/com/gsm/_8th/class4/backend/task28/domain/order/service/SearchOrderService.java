package com.gsm._8th.class4.backend.task28.domain.order.service;

import com.gsm._8th.class4.backend.task28.domain.order.controller.data.SearchOrderResponse;

public interface SearchOrderService {
    SearchOrderResponse execute(Long userId, int minPrice, int maxPrice, String address, String itemName, int page, int size);
}