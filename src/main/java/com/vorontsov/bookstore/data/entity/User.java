package com.vorontsov.bookstore.data.entity;


import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Converter;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

//import javax.persistence.AttributeConverter;
//import javax.persistence.Column;
//import javax.persistence.Convert;
//import javax.persistence.Converter;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.Table;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "surName")
    private String surName;

    @Column(name = "name")
    private String name;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "email", length = 75)
    private String email;

    @Column(name = "password", length = 75)
    private String password;

    @Column(name = "role")
    @Convert(converter = RoleConvert.class)
    private Role role;

    public enum Role {
        ADMIN,
        USER,
        MANAGER,
    }

    @Converter
    public class RoleConvert implements AttributeConverter<Role, Integer> {

        @Override
        public Integer convertToDatabaseColumn(Role role) {
            Integer idRole = null;
            switch (role) {
                case ADMIN -> {
                    idRole = 1;
                }
                case USER -> {
                    idRole = 2;
                }
                case MANAGER -> {
                    idRole = 3;
                }
            }
            return idRole;
        }

        @Override
        public Role convertToEntityAttribute(Integer integer) {
            Role role = null;
            switch (integer) {
                case 1 -> {
                    role = Role.valueOf("ADMIN");
                }
                case 2 -> {
                    role = Role.valueOf("USER");
                }
                case 3 -> {
                    role = Role.valueOf("MANAGER");
                }
            }
            return role;
        }
    }

}
