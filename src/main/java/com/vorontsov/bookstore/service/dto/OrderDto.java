package com.vorontsov.bookstore.service.dto;

import com.vorontsov.bookstore.data.entity.OrderItem;
import com.vorontsov.bookstore.data.entity.User;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderDto {
    private Long id;
    private LocalDateTime date;
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
