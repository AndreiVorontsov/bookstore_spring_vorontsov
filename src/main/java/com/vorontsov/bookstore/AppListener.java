package com.vorontsov.bookstore;


import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


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
        log.info("Context initialized");
    }


    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        log.debug("ServletContextDestroy Event");
        CONTEXT.close();
        log.info("Context destroyed");
    }
}
