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
    public static class RoleConvert implements AttributeConverter<Role, Integer> {

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
