package com.net.pixel.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {
    private static Properties properties;

    static {
        properties = new Properties();
        try (InputStream input = ConfigManager.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find config.properties");
            } else {
                properties.load(input);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    public static String getBaseUrl() {
        return getProperty("base.url");
    }

    public static String getBrowser() {
        return getProperty("browser");
    }

    public static long getImplicitWait() {
        return Long.parseLong(getProperty("implicit.wait"));
    }

    public static long getPageLoadTimeout() {
        return Long.parseLong(getProperty("page.load.timeout"));
    }
}