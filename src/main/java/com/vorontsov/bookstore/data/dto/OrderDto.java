package com.vorontsov.bookstore.data.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
public class OrderDto {
    private Long id;
    private Timestamp date;
    private Long user_id;
    private Status status;
    private BigDecimal price;

    public enum Status {
        PENDING,
        PAID,
        DELIVERED,
        CANCELED,
    }
}
