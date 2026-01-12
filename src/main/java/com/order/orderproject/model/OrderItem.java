package com.order.orderproject.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItem {
    private String name;
    private String category; // (ex: "ELECTRONICS", "BOOKS")
    private long price;
    private int quantity;
    // Constructeur complet pour faciliter le CommandLineRunner
    public OrderItem(String name, String category, long price, int quantity) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
    }
}
