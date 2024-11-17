package com.vorontsov.bookstore.data.connection;

import lombok.extern.log4j.Log4j2;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@Log4j2
public class DataSourceImpl implements Closeable, DataSource {
private ConnectionPool connectionPool;

    public DataSourceImpl(String url, String user, String password, int poolSize, String driver) {
        connectionPool = new ConnectionPool(driver, url, user, password, poolSize);
        log.info("Connection pool initialized");
    }

    @Override
    public Connection getConnection() throws SQLException {
        return connectionPool.getConnection();
    }

    @Override
    public void close() throws IOException {
        if (connectionPool != null) {
            connectionPool.destroyPool();
            connectionPool = null;
            log.info("Connection pool destroyed");
        }
    }

}
