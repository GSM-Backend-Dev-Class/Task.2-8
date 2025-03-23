package com.gsm._8th.class4.backend.task28.domain.order.controller.data;

import java.util.List;

public record SearchOrderResponse(
        int totalPage,
        long totalElements,
        List<OrderDto> searchOrders
) {
}