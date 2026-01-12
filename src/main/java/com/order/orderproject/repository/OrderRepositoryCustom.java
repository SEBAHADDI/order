package com.order.orderproject.repository;

import com.order.orderproject.dto.CategoryRevenueDTO;

import java.util.List;

public interface OrderRepositoryCustom {
    List<CategoryRevenueDTO> getRevenueByCategory();
}
