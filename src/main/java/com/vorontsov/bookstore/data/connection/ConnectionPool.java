package com.vorontsov.bookstore.data.connection;

import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

@Log4j2
class ConnectionPool {
    private final BlockingQueue<ProxyConnection> freeConnection;
    private final int poolSize;

    public ConnectionPool(String driver, String url, String user, String password, int poolSize) {
        this.freeConnection = new LinkedBlockingDeque<>();
        this.poolSize = poolSize;
        try {
            Class.forName(driver);
            log.info("Database driver loaded");
            for (int i = 0; i < this.poolSize; i++) {
                Connection connection = DriverManager.getConnection(url, user, password);
                freeConnection.add(new ProxyConnection(connection, this));
                log.info("Connection created");
            }
        } catch (SQLException | ClassNotFoundException e) {
//            e.printStackTrace();
            log.error(e.getMessage(),e);
        }
    }

    public Connection getConnection() {
        ProxyConnection connection = null;
        try {
            connection = freeConnection.take();
        } catch (InterruptedException e) {
            log.error(e.getMessage(),e);
        }
        return connection;
    }

    public void releaseConnection(Connection connection) {
        if (connection instanceof ProxyConnection proxyConnection) {
            freeConnection.add(proxyConnection);
        } else {
            log.warn("Returned not proxy connection");
        }
    }

    public void destroyPool() {
        for (int i = 0; i < poolSize; i++) {
            try {
                ProxyConnection connection = freeConnection.take();
                connection.reallyClose();
                log.info("Connection close");
            } catch (SQLException | InterruptedException e) {
                log.error(e.getMessage(),e);
            }
        }
    }
}
