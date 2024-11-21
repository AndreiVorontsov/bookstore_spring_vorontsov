package com.vorontsov.bookstore.data.connection;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

@Component
@Log4j2
public class ConnectionPool {
    private final BlockingQueue<ProxyConnection> freeConnection;
    private final int poolSize;

    @Autowired
    public ConnectionPool(@Value("${db.driver}") String driver,
                          @Value("${db.url}")String url,
                          @Value("${db.user}")String user,
                          @Value("${db.password}")String password,
                          @Value("${db.poolSize}")int poolSize) {
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
