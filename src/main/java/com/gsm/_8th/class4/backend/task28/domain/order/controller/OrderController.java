package com.gsm._8th.class4.backend.task28.domain.order.controller;

import com.gsm._8th.class4.backend.task28.domain.order.controller.data.OrderDto;
import com.gsm._8th.class4.backend.task28.domain.order.controller.data.SearchOrderResponse;
import com.gsm._8th.class4.backend.task28.domain.order.service.FindOrderByIdService;
import com.gsm._8th.class4.backend.task28.domain.order.service.SearchOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {

    private final SearchOrderService searchOrderService;
    private final FindOrderByIdService findOrderByIdService;

    @GetMapping("/search")
    public ResponseEntity<SearchOrderResponse> searchOrder(
            @RequestParam(value = "userId", required = false) Long userId,
            @RequestParam(value = "minPrice", required = false) Integer minPrice,
            @RequestParam(value = "maxPrice", required = false) Integer maxPrice,
            @RequestParam(value = "address", required = false) String address,
            @RequestParam(value = "itemName", required = false) String itemName,
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size
    ) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(searchOrderService.execute(userId, minPrice, maxPrice, address, itemName, page, size));
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDto> findOrderById(
            @PathVariable(value = "orderId") Long orderId
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(findOrderByIdService.execute(orderId));
    }
}