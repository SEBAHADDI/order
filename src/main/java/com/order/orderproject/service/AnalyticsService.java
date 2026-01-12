package com.order.orderproject.service;

import com.order.orderproject.dto.CategoryRevenueDTO;
import com.order.orderproject.model.Order;
import com.order.orderproject.repository.OrderRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Service
public class AnalyticsService {

    private final OrderRepository orderRepository;

    public AnalyticsService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<CategoryRevenueDTO> getStats() {
        // Le service appelle le repository, respectant ainsi l'architecture
        return orderRepository.getRevenueByCategory();
    }
}