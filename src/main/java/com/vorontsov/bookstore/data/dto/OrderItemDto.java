package com.vorontsov.bookstore.data.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class OrderItemDto {
    private Long id;
    private Long order_id;
    private Long book_id;
    private Integer quantity;
    private BigDecimal price;

}
