package com.vorontsov.bookstore.data.dao.impl;

import com.vorontsov.bookstore.data.dao.UserDAO;
import com.vorontsov.bookstore.data.dto.UserDto;
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
public class UserDAOImpl implements UserDAO {
    private static final String INSERT_SQL = "INSERT INTO users (surName,name,lastName,email,password,role) VALUES (?,?,?,?,?,(SELECT id FROM roles WHERE value = ?))";
    private static final String INSERT_NP_SQL = "INSERT INTO users (surName,name,lastName,email,password,role) VALUES (:surName,:name,:lastName,:email,:password,:role,(SELECT id FROM roles WHERE value = :value))";
    private static final String GET_ALL_USER_SQL = "SELECT u.id,u.surName,u.name,u.lastName,u.email,u.password,r.value FROM users u JOIN roles r ON u.role = r.id";
    private static final String GET_BY_EMAIL_SQL = "SELECT u.id,u.surName,u.name,u.lastName,u.email,u.password,r.value FROM users u JOIN roles r ON u.role = r.id WHERE u.email = ?";
    private static final String GET_BY_ID_SQL = "SELECT u.id,u.surName,u.name,u.lastName,u.email,u.password,r.value FROM users u JOIN roles r ON u.role = r.id WHERE u.id = ?";
    private static final String GET_BY_LASTNAME_SQL = "SELECT u.id,u.surName,u.name,u.lastName,u.email,u.password,r.value FROM users u JOIN roles r ON u.role = r.id WHERE u.lastName = ?";
    private static final String GET_COUNT_ALL_SQL = "SELECT count(*) FROM users";
    private static final String UPDATE_SQL = "UPDATE users SET surName = ?, name = ?, lastName = ?, email = ?,password = ?,role = (SELECT id FROM roles WHERE value = ?) where id = ?";
    private static final String UPDATE_NP_SQL = "UPDATE users SET surName = :surName, name = :name, lastName = :lastName, email = :email,password = :password,role = (SELECT id FROM roles WHERE value = :value) where id = :id";
    private static final String DEL_BY_EMAIL_SQL = "DELETE FROM books where email = ?";
    //    private final DataSource dataSource;
    private final JdbcTemplate template;
    private final NamedParameterJdbcTemplate namedTemplate;

    @Override
    public UserDto create(UserDto userDto) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
//        Map<String, Object> map = new HashMap<>();
//        map.put("surName", userDto.getSurName());
//        map.put("name", userDto.getName());
//        map.put("lastName", userDto.getLastName());
//        map.put("email", userDto.getEmail());
//        map.put("password", userDto.getPassword());
//        map.put("value", userDto.getRole());
////        map.put("id",user.getId());
//        namedTemplate.update(INSERT_NP_SQL, (SqlParameterSource) map, keyHolder);
//        Long id = keyHolder.getKeyAs(Long.class);
//        return findById(id);
        template.update((connection) -> {
            PreparedStatement statement = connection.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, userDto.getSurName());
            statement.setString(2, userDto.getName());
            statement.setString(3, userDto.getLastName());
            statement.setString(4, userDto.getEmail());
            statement.setString(5, userDto.getPassword());
            statement.setString(6, userDto.getRole().toString());
            return statement;
        }, keyHolder);
        Long id = keyHolder.getKeyAs(Long.class);
        return findById(id);
    }

    @Override
    public List<UserDto> getAll() {

        return template.query(GET_ALL_USER_SQL, this::mapRow);
    }

    @Override
    public UserDto findById(Long id) {

        return template.queryForObject(GET_BY_ID_SQL, this::mapRow, id);
    }

    @Override
    public UserDto findByEmail(String email) {

        return template.queryForObject(GET_BY_EMAIL_SQL, this::mapRow, email);
    }

    @Override
    public List<UserDto> findByLastName(String lastName) {

        return template.query(GET_BY_LASTNAME_SQL, this::mapRow);
    }

    @Override
    public UserDto update(UserDto userDto) {
        Map<String, Object> map = new HashMap<>();
        map.put("surName", userDto.getSurName());
        map.put("name", userDto.getName());
        map.put("lastName", userDto.getLastName());
        map.put("email", userDto.getEmail());
        map.put("password", userDto.getPassword());
        map.put("value", userDto.getRole());
        map.put("id", userDto.getId());
        namedTemplate.update(UPDATE_NP_SQL, map);

        return findById(userDto.getId());
    }

    @Override
    public boolean deleteByEmail(String email) {
        return template.update(DEL_BY_EMAIL_SQL, email) == 1;
    }

    @Override
    public long countAll() {
        return
                template.queryForObject(GET_COUNT_ALL_SQL, Integer.class);
    }

    private UserDto mapRow(ResultSet rs, int num) throws SQLException {
        UserDto userDto = new UserDto();
        userDto.setId(rs.getLong("id"));
        userDto.setSurName(rs.getString("surname"));
        userDto.setName(rs.getString("name"));
        userDto.setLastName(rs.getString("lastname"));
        userDto.setEmail(rs.getString("email"));
        userDto.setPassword(rs.getString("password"));
        userDto.setRole(UserDto.Role.valueOf(rs.getString("value").toString()));
        log.debug(userDto);
        return userDto;
    }
}
