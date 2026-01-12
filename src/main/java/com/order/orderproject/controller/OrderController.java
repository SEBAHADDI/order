package com.order.orderproject.controller;

import com.order.orderproject.dto.CategoryRevenueDTO;
import com.order.orderproject.model.Order;
import com.order.orderproject.service.AnalyticsService;
import com.order.orderproject.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;
    private final AnalyticsService analyticsService;

    public OrderController(OrderService orderService, AnalyticsService analyticsService) {
        this.orderService = orderService;
        this.analyticsService = analyticsService;
    }

    @PostMapping
    public Order save(@RequestBody Order order) {
        return orderService.createOrder(order);
    }

    @GetMapping
    public List<Order> findAll() {
        return orderService.getAllOrders();
    }

    @GetMapping("/status")
    public String checkStatus() {
        return "Order API is running";
    }

    @GetMapping("/getStats")
    public List<CategoryRevenueDTO> getStats() {
        return analyticsService.getStats();
    }
}
