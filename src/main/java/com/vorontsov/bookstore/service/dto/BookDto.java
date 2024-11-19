package com.vorontsov.bookstore.service.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Objects;

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