package com.vorontsov.bookstore.data.config;

import lombok.extern.log4j.Log4j2;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Log4j2
public class ConfigPropertiesImpl implements ConfigProperties {

    @Override
    public Properties getProperties(String Path) {
        Properties property = new Properties();
        try (InputStream input = ConfigPropertiesImpl.class.getClassLoader().getResourceAsStream(Path)) {
            property.load(input);
        } catch (IOException e) {
            log.error("No properties file: " + Path);
            throw new RuntimeException("No properties file: " + Path);
        }
        return property;
    }
}

