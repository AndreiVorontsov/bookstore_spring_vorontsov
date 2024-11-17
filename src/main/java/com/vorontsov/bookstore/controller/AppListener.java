package com.vorontsov.bookstore.controller;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import lombok.extern.log4j.Log4j2;

@WebListener
@Log4j2
public class AppListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
       CommandFactory instance = CommandFactory.INSTANCE;
        log.info("Context initialized" +instance.hashCode());
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        log.debug("ServletContextDestroy Event");
        CommandFactory.INSTANCE.close();
        log.info("Context destroyed");
    }
}
