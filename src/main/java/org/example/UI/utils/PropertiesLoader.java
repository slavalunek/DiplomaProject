package org.example.UI.utils;

import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Log4j2
public class PropertiesLoader {

    public static final String CONFIG_PROPERTIES = "config.properties";

    public static Properties loadProperties(String fileName) {
        Properties properties = new Properties();

        try (InputStream input = PropertiesLoader.class.getClassLoader().getResourceAsStream(fileName)) {
            properties.load(input);
            log.info("Properties file \"{}\" was read successfully", fileName);
        } catch (IOException ex) {
            log.error("Properties file \"{}\" was not read! \nError: {}", fileName, ex.getCause());
        }
        return properties;
    }

    public static Properties loadProperties() {
        Properties properties = loadProperties(CONFIG_PROPERTIES);
        return properties;
    }
}