package com.vorontsov.bookstore.data.repository.impl;


import com.vorontsov.bookstore.data.entity.Order;
import com.vorontsov.bookstore.data.repository.OrderRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class OrderRepositoryImpl implements OrderRepository {
    private static final String GET_COUNT_ALL = "count(*) from Order";
    public static final String GET_ALL = "from Order";
    private static final String GET_BY_USER_ID = "from Order where user.id = ?1";

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Order save(Order order) {
        if (order.getId() != null) {
            manager.merge(order);
        } else {
            manager.persist(order);
        }
        return order;
    }

    @Override
    public List<Order> getAll() {
        return manager
                .createQuery(GET_ALL, Order.class)
                .getResultList();
    }

    @Override
    public Optional<Order> findById(Long id) {
        return Optional.ofNullable(manager.find(Order.class, id));
    }

    @Override
    public List<Order> findByUserId(Long user_id) {
        return manager
                .createQuery(GET_BY_USER_ID, Order.class)
                .setParameter(1, user_id)
                .getResultList();
    }

    @Override
    public long countAll() {
        return manager
                .createQuery(GET_COUNT_ALL, Long.class)
                .getSingleResult();
    }


}
