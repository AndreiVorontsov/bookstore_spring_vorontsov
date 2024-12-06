package com.vorontsov.bookstore.data.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.EnumType;
//import javax.persistence.Enumerated;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.Table;
import java.math.BigDecimal;


@Entity
@Table(name = "books")
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "author")
    private String author;

    @Column(name = "isbn", unique = true, length = 13)
    private String isbn;

    @Column(name = "cover")
    @Enumerated(EnumType.STRING)
    private Cover cover;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "year_publication")
    private Integer yearPublication;

    @Column(name = "delete")
    private boolean delete;

    public enum Cover {
        SOFT,
        HARD,
        SPECIAL,
    }

}