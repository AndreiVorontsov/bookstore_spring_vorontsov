package com.vorontsov.bookstore.data.dao.impl;

import com.vorontsov.bookstore.data.dao.OrderItemDAO;
import com.vorontsov.bookstore.data.dto.OrderItemDto;
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
public class OrderItemDAOImpl implements OrderItemDAO {
    private static final String INSERT_NP_SQL = "INSERT INTO order_items (book_id,quantity,price,order_id) VALUES (:book_id,:quantity,:price,:order_id)";
    private static final String GET_ITEM_ALL_SQL = "select id,book_id,quantity,price,order_id FROM order_items";
    private static final String GET_BY_ID_SQL = "SELECT id,book_id,quantity,price,order_id FROM order_items WHERE id = ?";
    private static final String GET_BY_ORDER_ID_SQL = "SELECT id,book_id,quantity,price,order_id FROM order_items WHERE order_id = ?";
    private static final String GET_BY_BOOK_ID_SQL = "SELECT id,book_id,quantity,price,order_id FROM order_items WHERE book_id = ?";
    private static final String GET_COUNT_ALL_SQL = "SELECT count(*) FROM order_items";
    private static final String UPDATE_NP_SQL = "UPDATE order_items SET book_id = :book_id, quantity = :quantity, price = :price, order_id = :order_id  where id = :id";
    private static final String DEL_BY_ID_SQL = "DELETE FROM order_items where id = ?";

    private final JdbcTemplate template;
    private final NamedParameterJdbcTemplate namedTemplate;

    @Override
    public OrderItemDto create(OrderItemDto orderItemDto) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        Map<String, Object> map = new HashMap<>();
        map.put("book_id", orderItemDto.getBook_id());
        map.put("quantity", orderItemDto.getQuantity());
        map.put("price", orderItemDto.getPrice());
        map.put("order_id", orderItemDto.getOrder_id());
        namedTemplate.update(INSERT_NP_SQL, (SqlParameterSource) map, keyHolder);
        Long id = keyHolder.getKeyAs(Long.class);
        return findById(id);
    }

    @Override
    public List<OrderItemDto> getAll() {
        return template.query(GET_ITEM_ALL_SQL, this::mapRow);
    }

    @Override
    public List<OrderItemDto> findByOrderId(Long orderId) {
        return template.query(GET_BY_ORDER_ID_SQL, this::mapRow, orderId);
    }

    @Override
    public OrderItemDto findById(Long id) {
        return template.queryForObject(GET_BY_ID_SQL, this::mapRow, id);
    }

    @Override
    public List<OrderItemDto> findByBookId(Long bookId) {
        return template.query(GET_BY_BOOK_ID_SQL, this::mapRow, bookId);
    }

    @Override
    public long countAll() {
        return template.queryForObject(GET_COUNT_ALL_SQL, Integer.class);
    }

//    id,book_id,quantity,price,order_id

    @Override
    public OrderItemDto update(OrderItemDto orderItemDto) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", orderItemDto.getId());
        map.put("book_id", orderItemDto.getBook_id());
        map.put("quantity", orderItemDto.getQuantity());
        map.put("price", orderItemDto.getPrice());
        map.put("order_id", orderItemDto.getOrder_id());
        namedTemplate.update(UPDATE_NP_SQL, map);

        return findById(orderItemDto.getId());
    }

    @Override
    public boolean deleteById(Long id) {
        return template.update(DEL_BY_ID_SQL, id) == 1;
    }

    private OrderItemDto mapRow(ResultSet rs, int num) throws SQLException {
        OrderItemDto orderItemDto = new OrderItemDto();
        orderItemDto.setId(rs.getLong("id"));
        orderItemDto.setBook_id(rs.getLong("book_id"));
        orderItemDto.setQuantity(rs.getInt("quantity"));
        orderItemDto.setPrice(rs.getBigDecimal("price"));
        orderItemDto.setOrder_id(rs.getLong("order_id"));
        log.debug(orderItemDto);
        return orderItemDto;
    }
}
