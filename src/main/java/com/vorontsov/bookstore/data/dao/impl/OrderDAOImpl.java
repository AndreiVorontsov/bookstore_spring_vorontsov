package com.vorontsov.bookstore.data.dao.impl;

import com.vorontsov.bookstore.data.dao.OrderDAO;
import com.vorontsov.bookstore.data.dto.OrderDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
@Log4j2
public class OrderDAOImpl implements OrderDAO {
    private static final String INSERT_NP_SQL = "INSERT INTO orders (date,user_id,status,price) VALUES (:date,:user_id,status = (SELECT id FROM statuses WHERE value = :value),:price)";
    private static final String GET_ORDERS_ALL_SQL = "SELECT o.id,o.date,o.user_id,s.value,o.price FROM orders o JOIN statuses s ON s.id = o.status";
    private static final String GET_BY_ID_SQL = "SELECT o.id,o.date,o.user_id,s.value,o.price FROM orders o JOIN statuses s ON s.id = o.status WHERE o.id = ?";
    private static final String GET_BY_USERID_SQL = "SELECT o.id,o.date,o.user_id,s.value,o.price FROM orders o JOIN statuses s ON s.id = o.status WHERE o.user_id = ?";
    private static final String GET_BY_STATUS_SQL = "SELECT o.id,o.date,o.user_id,s.value,o.price FROM orders o JOIN statuses s ON s.id = o.status WHERE s.value = ?";
    private static final String GET_COUNT_ALL_SQL = "SELECT count(*) FROM orders";
    private static final String UPDATE_NP_SQL = "UPDATE orders SET date = :date, user_id = :user_id, status = (SELECT id FROM statuses WHERE value = :value), price = :price where id = :id";
    private static final String DEL_BY_ID_SQL = "DELETE FROM orders where id = ?";

    private final JdbcTemplate template;
    private final NamedParameterJdbcTemplate namedTemplate;

    @Override
    public OrderDto create(OrderDto orderDto) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        Map<String, Object> map = new HashMap<>();
        map.put("date", orderDto.getDate());
        map.put("user_id", orderDto.getUser_id());
        map.put("price", orderDto.getPrice());
        map.put("value", orderDto.getStatus());
        namedTemplate.update(INSERT_NP_SQL, (SqlParameterSource) map, keyHolder);
        Long id = keyHolder.getKeyAs(Long.class);
        return findById(id);
    }

    @Override
    public List<OrderDto> getAll() {

        return template.query(GET_ORDERS_ALL_SQL, this::mapRow);
    }

    @Override
    public List<OrderDto> findByStatus(String status) {
        return template.query(GET_BY_STATUS_SQL, this::mapRow, status);
    }

    @Override
    public OrderDto findById(Long id) {
        return template.queryForObject(GET_BY_ID_SQL, this::mapRow, id);
    }

    @Override
    public List<OrderDto> findByUserId(Long userId) {
        return template.query(GET_BY_USERID_SQL, this::mapRow, userId);
    }

    @Override
    public long countAll() {
        return template.queryForObject(GET_COUNT_ALL_SQL, Integer.class);
    }

    @Override
    public OrderDto update(OrderDto orderDto) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", orderDto.getId());
        map.put("date", orderDto.getDate());
        map.put("user_id", orderDto.getUser_id());
        map.put("value", orderDto.getStatus());
        map.put("price", orderDto.getPrice());
        namedTemplate.update(UPDATE_NP_SQL, map);

        return findById(orderDto.getId());
    }

    @Override
    public boolean deleteById(Long id) {
        return template.update(DEL_BY_ID_SQL, id) == 1;
    }

    private OrderDto mapRow(ResultSet rs, int num) throws SQLException {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(rs.getLong("id"));
        orderDto.setDate(rs.getTimestamp("date"));
        orderDto.setUser_id(rs.getLong("user_id"));
        orderDto.setStatus(OrderDto.Status.valueOf(rs.getString("value").toString()));
        orderDto.setPrice(rs.getBigDecimal("price"));
        log.debug(orderDto);
        return orderDto;
    }

}
