package com.order.orderproject.repository;

import com.order.orderproject.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends MongoRepository<Order, String>, OrderRepositoryCustom {
}

