package com.vorontsov.bookstore;

import com.vorontsov.bookstore.AppConfig;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;


@WebListener
@Log4j2
public class AppListener implements ServletContextListener {

    private static AnnotationConfigApplicationContext CONTEXT;

    public static AnnotationConfigApplicationContext getContext() {
        return CONTEXT;
    }


    @Override
    public void contextInitialized(ServletContextEvent sce) {
        CONTEXT = new AnnotationConfigApplicationContext(AppConfig.class);
        System.out.println("Context initialized");
//       CommandFactory instance = CommandFactory.INSTANCE;
        log.info("Context initialized"); //+instance.hashCode());
    }


    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        log.debug("ServletContextDestroy Event");
        CONTEXT.close();
//        CommandFactory.INSTANCE.close();
        log.info("Context destroyed");
    }
}
