package com.vorontsov.bookstore.data.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BookDto {

    private Long id;
    private String name;
    private String author;
    private String isbn;
    private Cover cover;
    private BigDecimal price;
    private int yearPublication;
    private boolean delete;


    public enum Cover {
        SOFT,
        HARD,
        SPECIAL,
    }
}