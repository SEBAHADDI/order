package com.order.orderproject.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.List;

@Document(collection = "orders")
@Data
public class Order {
    @Id
    private String id;
    private Instant createdDate;
    private String product;
    private long price;
    private String customerName;
    private int quantity;
    private String status; // ex: "CONFIRMED", "PENDING"
    private List<OrderItem> items;
}


