package com.vorontsov.bookstore.data.entity;

import lombok.Data;

import java.math.BigDecimal;


@Data
public class Book {
    private Long id;
    private String name;
    private String author;
    private String isbn;
    private Cover cover;
    private BigDecimal price;
    private Integer yearPublication;
    private boolean delete;

    public enum Cover {
        SOFT,
        HARD,
        SPECIAL,
    }
}