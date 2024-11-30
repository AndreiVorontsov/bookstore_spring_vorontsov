package com.vorontsov.bookstore.data.dao.impl;

import com.vorontsov.bookstore.data.dao.BookDAO;
import com.vorontsov.bookstore.data.entity.Book;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.vorontsov.bookstore.data.entity.Book.Cover;

@Repository
@RequiredArgsConstructor
@Log4j2
public class BookDAOJBDCImpl implements BookDAO {

    private static final String INSERT_SQL = "insert into books (name, author, isbn, cover,price,year_Publication) values (?,?,?,?,?,?)";
    private static final String GET_ALL_BOOK_SQL = "select id,name,author,Isbn,cover,Price,Year_Publication,delete from books";
    private static final String GET_BY_ID_SQL = "select id,name,author,Isbn,cover,Price,Year_Publication,delete from books where id = ?";
    private static final String GET_BY_ISBN_SQL = "select id,name,author,Isbn,cover,Price,Year_Publication,delete from books where ISBN = ?";
    private static final String DEL_BY_ID_SQL = "DELETE FROM books where id = ?";
    private static final String GET_BY_AUTHOR_SQL = "select id,name,author,Isbn,cover,Price,Year_Publication,delete from books where Author = ?";
    private static final String UPDATE_NP_SQL = "update books set name = :name, author = :author, isbn = :isbn, cover = :cover,price = :price,Year_Publication = :Year_Publication where id = :id";
    private static final String SOFT_DELETE_NP_SQL = "update books set delete = :delete where id = :id";
    private static final String GET_COUNT_ALL_SQL = "select count(*) from books";
    private final JdbcTemplate template;
    private final NamedParameterJdbcTemplate namedTemplate;


    @Override
    public Book create(Book book) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        template.update((connection) -> {
            PreparedStatement statement = connection.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, book.getName());
            statement.setString(2, book.getAuthor());
            statement.setString(3, book.getIsbn());
            statement.setString(4, String.valueOf(book.getCover()));
            statement.setBigDecimal(5, book.getPrice());
            statement.setInt(6, book.getYearPublication());
            return statement;
        }, keyHolder);
        Long id = keyHolder.getKeyAs(Long.class);
        return getById(id);

    }

    @Override
    public List<Book> getAll() {
        return template.query(GET_ALL_BOOK_SQL, this::mapRow);
    }

    @Override
    public Book getById(long id) {
        return template.queryForObject(GET_BY_ID_SQL, this::mapRow, id);
    }

    @Override
    public Book update(Book book) {
        Map<String, Object> map = new HashMap<>();
        map.put("name", book.getName());
        map.put("author", book.getAuthor());
        map.put("isbn", book.getIsbn());
        map.put("cover", book.getCover());
        map.put("price", book.getPrice());
        map.put("Year_Publication", book.getYearPublication());
        map.put("id", book.getId());
        namedTemplate.update(UPDATE_NP_SQL, map);

        return getById(book.getId());

    }

    @Override
    public boolean deleteById(long id) {
        return template.update(DEL_BY_ID_SQL, id) == 1;
    }

    @Override
    public boolean softDeleteById(long id, boolean bool) {
        Map<String, Object> map = new HashMap<>();
        map.put("delete", bool);
        map.put("id", id);
        namedTemplate.update(SOFT_DELETE_NP_SQL, map);
        return bool == getById(id).isDelete();
    }

    @Override
    public Book findByIsbn(String isbn) {
        return template.queryForObject(GET_BY_ISBN_SQL, this::mapRow, isbn);
    }

    @Override
    public List<Book> findByAuthor(String author) {
        return template.query(GET_BY_AUTHOR_SQL, this::mapRow, author);
    }

    @Override
    public long countAll() {
        return template.queryForObject(GET_COUNT_ALL_SQL, Integer.class);
    }

    private Book mapRow(ResultSet rs, int num) throws SQLException {
        Book book = new Book();
        book.setId(rs.getLong("id"));
        book.setName(rs.getString("name"));
        book.setAuthor(rs.getString("author"));
        book.setIsbn(rs.getString("Isbn"));
        book.setCover(Cover.valueOf(rs.getString("cover")));
        book.setPrice(rs.getBigDecimal("Price"));
        book.setYearPublication(rs.getInt("Year_Publication"));
        book.setDelete(rs.getBoolean("delete"));
        log.debug(book);
        return book;
    }
}
