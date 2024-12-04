package com.vorontsov.bookstore.data.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Data
public class Order {
    private Long id;
    private Timestamp date;
    private User user;
    private Status status;
    private BigDecimal price;
    private List<OrderItem> OrderItems;

    public enum Status {
        PENDING,
        PAID,
        DELIVERED,
        CANCELED,
    }
}
