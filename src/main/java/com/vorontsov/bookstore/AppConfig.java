package com.vorontsov.bookstore;

import com.vorontsov.bookstore.controller.FrontController;
import org.eclipse.tags.shaded.org.apache.xalan.lib.sql.ConnectionPool;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@Configuration
@ComponentScan
@PropertySource("classpath:/application.properties")
public class AppConfig {

}
