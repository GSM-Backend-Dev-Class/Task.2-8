package com.gsm._8th.class4.backend.task28.domain.order.controller;

import com.gsm._8th.class4.backend.task28.domain.order.controller.data.OrderDto;
import com.gsm._8th.class4.backend.task28.domain.order.controller.data.SearchOrderResponse;
import com.gsm._8th.class4.backend.task28.domain.order.service.FindOrderByIdService;
import com.gsm._8th.class4.backend.task28.domain.order.service.SearchOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {

    private final SearchOrderService searchOrderService;
    private final FindOrderByIdService findOrderByIdService;

    @GetMapping("/search")
    public ResponseEntity<SearchOrderResponse> searchOrder(
            @RequestParam("userId") Long userId,
            @RequestParam("minPrice") int minPrice,
            @RequestParam("maxPrice") int maxPrice,
            @RequestParam("address") String address,
            @RequestParam("itemName") String itemName,
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(searchOrderService.execute(userId, minPrice, maxPrice, address, itemName, page, size));
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDto> findOrderById(
            @RequestParam("orderId") Long orderId
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(findOrderByIdService.execute(orderId));
    }
}