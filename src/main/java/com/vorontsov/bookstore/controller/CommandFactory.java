package com.vorontsov.bookstore.controller;

import com.vorontsov.bookstore.controller.impl.BookCommand;
import com.vorontsov.bookstore.controller.impl.BooksCommand;
import com.vorontsov.bookstore.controller.impl.ErrorCommand;
import com.vorontsov.bookstore.controller.impl.HomeCommand;
import com.vorontsov.bookstore.controller.impl.UserCommand;
import com.vorontsov.bookstore.controller.impl.UsersCommand;
import com.vorontsov.bookstore.data.config.ConfigPropertiesImpl;
import com.vorontsov.bookstore.data.connection.DataSource;
import com.vorontsov.bookstore.data.connection.DataSourceImpl;
import com.vorontsov.bookstore.data.dao.BookDAO;
import com.vorontsov.bookstore.data.dao.UserDAO;
import com.vorontsov.bookstore.data.dao.impl.BookDAOJBDCImpl;
import com.vorontsov.bookstore.data.dao.impl.UserDAOImpl;
import com.vorontsov.bookstore.service.ServiceBook;
import com.vorontsov.bookstore.service.ServiceUser;
import com.vorontsov.bookstore.service.impl.ServiceBookImpl;
import com.vorontsov.bookstore.service.impl.ServiceUserImpl;
import com.vorontsov.bookstore.service.mapper.Mapper;
import com.vorontsov.bookstore.service.mapper.MapperImpl;
import lombok.extern.log4j.Log4j2;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@Log4j2
public class CommandFactory implements Closeable {
    public static final CommandFactory INSTANCE = new CommandFactory();
    private final Map<String, Command> map;
    private final List<Closeable> closeables;

     private CommandFactory() {
        map = new HashMap<>();
        closeables = new ArrayList<>();

        ConfigPropertiesImpl configProperties = new ConfigPropertiesImpl();
        Properties property = configProperties.getProperties("config.properties");

        String url = property.getProperty("db.url");
        String user = property.getProperty("db.user");
        String password = property.getProperty("db.password");
        int poolSize = Integer.parseInt(property.getProperty("db.poolSize"));
        String driver = property.getProperty("db.driver");
        DataSource dataSource = new DataSourceImpl(url, user, password, poolSize, driver);
        closeables.add(dataSource);

        BookDAO bookDAO = new BookDAOJBDCImpl(dataSource);
        UserDAO userDAO = new UserDAOImpl(dataSource);

        Mapper mapper = new MapperImpl();

        ServiceBook serviceBook = new ServiceBookImpl(bookDAO, mapper);
        ServiceUser serviceUser = new ServiceUserImpl(userDAO, mapper);

        map.put("book", new BookCommand(serviceBook));
        map.put("books", new BooksCommand(serviceBook));
        map.put("user", new UserCommand(serviceUser));
        map.put("users", new UsersCommand(serviceUser));
        map.put("error", new ErrorCommand());
        map.put("home", new HomeCommand());
    }

    public Command get(String commandParam) {
         Command command = map.get(commandParam);
         if (command == null){
             command = map.get("error");
         }
        return command;

    }

    @Override
    public void close() {
        for (Closeable closeable : closeables){
            try {
                closeable.close();
            } catch (IOException e) {
                log.error(e);
            }
        }
    }
}
