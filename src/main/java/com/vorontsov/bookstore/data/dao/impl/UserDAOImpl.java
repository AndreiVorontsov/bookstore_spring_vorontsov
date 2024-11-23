package com.vorontsov.bookstore.data.dao.impl;

import com.vorontsov.bookstore.data.connection.DataSource;
import com.vorontsov.bookstore.data.dao.UserDAO;
import com.vorontsov.bookstore.data.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
@Log4j2
public class UserDAOImpl implements UserDAO {
    private static final String INSERT_SQL = "INSERT INTO users (surName,name,lastName,email,password,role) VALUES (?,?,?,?,?,(SELECT id FROM roles WHERE value = ?))";
    private static final String GET_ALL_USER_SQL = "SELECT u.id,u.surName,u.name,u.lastName,u.email,u.password,r.value FROM users u JOIN roles r ON u.role = r.id";
    private static final String GET_BY_EMAIL_SQL = "SELECT u.id,u.surName,u.name,u.lastName,u.email,u.password,r.value FROM users u JOIN roles r ON u.role = r.id WHERE u.email = ?";
    private static final String GET_BY_LASTNAME_SQL = "SELECT u.id,u.surName,u.name,u.lastName,u.email,u.password,r.value FROM users u JOIN roles r ON u.role = r.id WHERE u.lastName = ?";
    private static final String GET_COUNT_ALL_SQL = "SELECT count(*) FROM users";
    private static final String UPDATE_SQL = "UPDATE users SET surName = ?, name = ?, lastName = ?, email = ?,password = ?,role = (SELECT id FROM roles WHERE value = ?) where id = ?";
    private static final String UPDATE_NP_SQL = "UPDATE users SET surName = :surName, name = :name, lastName = :lastName, email = :email,password = :password,role = (SELECT id FROM roles WHERE value = :value) where id = :id";
    private static final String DEL_BY_EMAIL_SQL = "DELETE FROM books where email = ?";
    private final DataSource dataSource;
    private final JdbcTemplate template;
    private final NamedParameterJdbcTemplate namedTemplate;

    @Override
    public User create(User user) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(INSERT_SQL);
            statement.setString(1, user.getSurName());
            statement.setString(2, user.getName());
            statement.setString(3, user.getLastName());
            statement.setString(4, user.getEmail());
            statement.setString(5, user.getPassword());
            statement.setString(6, user.getRole().toString());
            log.debug("Create" + user);
            statement.executeUpdate();

            return findByEmail(user.getEmail());

        } catch (SQLException e) {
            log.error("Create" + user);
            throw new RuntimeException("user :" + user, e);
        }
    }

    @Override
    public List<User> getAll() {
        return template.query(GET_ALL_USER_SQL, this::mapRow);
    }

    private User process(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong("id"));
        user.setSurName(resultSet.getString("surname"));
        user.setName(resultSet.getString("name"));
        user.setLastName(resultSet.getString("lastname"));
        user.setEmail(resultSet.getString("email"));
        user.setPassword(resultSet.getString("password"));
        user.setRole(User.Role.valueOf(resultSet.getString("value").toString()));
        log.debug(user);
        return user;
    }

    @Override
    public User findByEmail(String email) {
        return template.queryForObject(GET_BY_EMAIL_SQL, this::mapRow, email);
    }

    @Override
    public List<User> findByLastName(String lastName) {
        List<User> users = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(GET_BY_LASTNAME_SQL);
            statement.setString(1, lastName);
            log.debug("Find by LastName " + lastName + ":");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                users.add(process(resultSet));
            }
        } catch (SQLException e) {
            log.error("Find by LastName " + lastName);
            throw new RuntimeException(e);
        }
        return users;
    }

    @Override
    public User update(User user) {
        Map<String,Object> map = new HashMap<>();
        map.put("surName",user.getSurName());
        map.put("name",user.getName());
        map.put("lastName",user.getLastName());
        map.put("email",user.getEmail());
        map.put("password",user.getPassword());
        map.put("value",user.getRole());
        map.put("id",user.getId());
        namedTemplate.update(UPDATE_NP_SQL,map);

        return findByEmail(user.getEmail());
    }

    @Override
    public boolean deleteByEmail(String email) {
        return template.update(DEL_BY_EMAIL_SQL, email) == 1;
    }

    @Override
    public long countAll() {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(GET_COUNT_ALL_SQL);
            log.debug("Get count ALL");
            if (statement.execute()) {
                ResultSet resultSet = statement.getResultSet();
                resultSet.next();
                return resultSet.getLong(1);
            }
        } catch (SQLException e) {
            log.error("Get count ALL");
            throw new RuntimeException(e);
        }
        throw new RuntimeException("");
    }

    private User mapRow(ResultSet rs, int num) throws SQLException {
        User user = new User();
        user.setId(rs.getLong("id"));
        user.setSurName(rs.getString("surname"));
        user.setName(rs.getString("name"));
        user.setLastName(rs.getString("lastname"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        user.setRole(User.Role.valueOf(rs.getString("value").toString()));
        log.debug(user);
        return user;
    }
}
