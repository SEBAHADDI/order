package com.order.orderproject.service;

import com.order.orderproject.model.Order;

import java.util.List;

public interface OrderService {
    Order createOrder(Order order);
    List<Order> getAllOrders();
}

