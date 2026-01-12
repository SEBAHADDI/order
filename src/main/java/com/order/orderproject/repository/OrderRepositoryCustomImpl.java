package com.order.orderproject.repository;

import com.order.orderproject.dto.CategoryRevenueDTO;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.ArithmeticOperators;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderRepositoryCustomImpl implements OrderRepositoryCustom {

    private final MongoTemplate mongoTemplate;

    public OrderRepositoryCustomImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<CategoryRevenueDTO> getRevenueByCategory() {
        // Tu colles ici le code d'agrégation que je t'ai donné avant
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("status").is("CONFIRMED")),
                Aggregation.unwind("items"),
                Aggregation.group("items.category")
                        .sum(ArithmeticOperators.Multiply.valueOf("items.price").multiplyBy("items.quantity"))
                        .as("totalRevenue"),
                Aggregation.project("totalRevenue").and("_id").as("category")
        );

        return mongoTemplate.aggregate(aggregation, "orders", CategoryRevenueDTO.class)
                .getMappedResults();
    }
}
