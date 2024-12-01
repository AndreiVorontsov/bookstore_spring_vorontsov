package com.vorontsov.bookstore.data.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class OrderDto {
    private Long id;
    private LocalDateTime timeStamp;
    private Long userId;
    private Status status;
    private BigDecimal price;

    public enum Status {
        PENDING,
        PAID,
        DELIVERED,
        CANCELED,
    }
}
