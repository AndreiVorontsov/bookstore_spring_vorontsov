package com.vorontsov.bookstore.data.connection;

import org.springframework.stereotype.Component;

import java.io.Closeable;
import java.sql.Connection;
import java.sql.SQLException;


public interface DataSource extends Closeable {
    Connection getConnection() throws SQLException;

}
