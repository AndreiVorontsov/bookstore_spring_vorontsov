package com.vorontsov.bookstore.data.entity;


import jakarta.persistence.AttributeConverter;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Converter;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime date;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "status")
    @Convert(converter = StatusConvert.class)
    private Status status;

    @Column(name = "price")
    private BigDecimal price;

    @OneToMany(mappedBy = "order", cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private List<OrderItem> orderItems;

    public enum Status {
        PENDING,
        PAID,
        DELIVERED,
        CANCELED,
    }

    @Converter
    public static class StatusConvert implements AttributeConverter<Status, Integer> {

        @Override
        public Integer convertToDatabaseColumn(Status status) {
            Integer idStatus = null;
            switch (status) {
                case PENDING -> idStatus = 1;
                case PAID -> idStatus = 2;
                case DELIVERED -> idStatus = 3;
                case CANCELED -> idStatus = 4;
            }
            return idStatus;
        }

        @Override
        public Status convertToEntityAttribute(Integer integer) {
            Status status = null;
            switch (integer) {
                case 1 -> status = Status.valueOf("PENDING");
                case 2 -> status = Status.valueOf("PAID");
                case 3 -> status = Status.valueOf("DELIVERED");
                case 4 -> status = Status.valueOf("CANCELED");
                default -> throw new IllegalStateException("Unexpected value status: " + integer);
            }
            return status;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return getId().equals(order.getId()) && getDate().equals(order.getDate()) && getUser().equals(order.getUser()) && getStatus() == order.getStatus() && getPrice().equals(order.getPrice()) && getOrderItems().equals(order.getOrderItems());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDate(), getUser(), getStatus(), getPrice(), getOrderItems());
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", date=" + date +
                ", user=" + user +
                ", status=" + status +
                ", price=" + price +
                ", orderItems=" + orderItems.size() +
                '}';
    }
}
