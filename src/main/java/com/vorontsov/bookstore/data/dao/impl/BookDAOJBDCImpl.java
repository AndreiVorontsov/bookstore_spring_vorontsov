package com.vorontsov.bookstore.data.dao.impl;

import com.vorontsov.bookstore.data.dao.BookDAO;
import com.vorontsov.bookstore.data.dto.BookDto;
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
    public BookDto create(BookDto bookDto) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        template.update((connection) -> {
            PreparedStatement statement = connection.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, bookDto.getName());
            statement.setString(2, bookDto.getAuthor());
            statement.setString(3, bookDto.getIsbn());
            statement.setString(4, String.valueOf(bookDto.getCover()));
            statement.setBigDecimal(5, bookDto.getPrice());
            statement.setInt(6, bookDto.getYearPublication());
            return statement;
        }, keyHolder);
        Long id = keyHolder.getKeyAs(Long.class);
        return getById(id);

    }

    @Override
    public List<BookDto> getAll() {

        return template.query(GET_ALL_BOOK_SQL, this::mapRow);
    }

    @Override
    public BookDto getById(long id) {

        return template.queryForObject(GET_BY_ID_SQL, this::mapRow, id);
    }

    @Override
    public BookDto update(BookDto bookDto) {
        Map<String, Object> map = new HashMap<>();
        map.put("name", bookDto.getName());
        map.put("author", bookDto.getAuthor());
        map.put("isbn", bookDto.getIsbn());
        map.put("cover", bookDto.getCover());
        map.put("price", bookDto.getPrice());
        map.put("Year_Publication", bookDto.getYearPublication());
        map.put("id", bookDto.getId());
        namedTemplate.update(UPDATE_NP_SQL, map);

        return getById(bookDto.getId());

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
    public BookDto findByIsbn(String isbn) {

        return template.queryForObject(GET_BY_ISBN_SQL, this::mapRow, isbn);
    }

    @Override
    public List<BookDto> findByAuthor(String author) {

        return template.query(GET_BY_AUTHOR_SQL, this::mapRow, author);
    }

    @Override
    public long countAll() {

        return template.queryForObject(GET_COUNT_ALL_SQL, Integer.class);
    }

    private BookDto mapRow(ResultSet rs, int num) throws SQLException {
        BookDto bookDto = new BookDto();
        bookDto.setId(rs.getLong("id"));
        bookDto.setName(rs.getString("name"));
        bookDto.setAuthor(rs.getString("author"));
        bookDto.setIsbn(rs.getString("Isbn"));
        bookDto.setCover(BookDto.Cover.valueOf(rs.getString("cover")));
        bookDto.setPrice(rs.getBigDecimal("Price"));
        bookDto.setYearPublication(rs.getInt("Year_Publication"));
        bookDto.setDelete(rs.getBoolean("delete"));
        log.debug(bookDto);
        return bookDto;
    }
}
