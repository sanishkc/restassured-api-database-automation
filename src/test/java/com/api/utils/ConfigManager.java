package com.api.utils;

import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {

	/*
	 * private static Properties properties = new Properties();
	 * 
	 * static { try { InputStream input = ConfigManager.class .getClassLoader()
	 * .getResourceAsStream("config.properties"); properties.load(input); } catch
	 * (Exception e) { throw new
	 * RuntimeException("Failed to load config.properties"); } }
	 * 
	 * public static String get(String key) { return properties.getProperty(key); }
	 */
	


    private static final Properties properties = new Properties();

    static {
        try (InputStream input = ConfigManager.class
                .getClassLoader()
                .getResourceAsStream("config.properties")) {

            properties.load(input);

        } catch (Exception e) {
            throw new RuntimeException("Failed to load config.properties", e);
        }
    }

    public static String get(String key) {
        String value = properties.getProperty(key);

        if (value == null) {
            throw new RuntimeException("Property not found: " + key);
        }

        return resolveEnvVariables(value);
    }

    /**
     * Resolves ${ENV_VAR:default} syntax
     */
    private static String resolveEnvVariables(String value) {

        if (!value.contains("${")) {
            return value;
        }

        int start = value.indexOf("${");
        int end = value.indexOf("}");

        String expression = value.substring(start + 2, end); // BASE_URL:http://localhost:8081
        String[] parts = expression.split(":", 2);

        String envKey = parts[0];
        String defaultValue = parts.length > 1 ? parts[1] : "";

        String envValue = System.getenv(envKey);

        return envValue != null ? envValue : defaultValue;
    }
	
	
}
