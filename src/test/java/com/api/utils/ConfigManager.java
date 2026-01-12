package com.api.utils;

import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {

    // Stores all properties loaded from config.properties
    private static final Properties properties = new Properties();

    static {
        // Load the properties file when the class is first loaded
        try (InputStream input = ConfigManager.class
                .getClassLoader()
                .getResourceAsStream("config.properties")) {

            // Load key-value pairs into the Properties object
            properties.load(input);

        } catch (Exception e) {
            // Throw a runtime exception if the file cannot be read
            throw new RuntimeException("Failed to load config.properties", e);
        }
    }

    /**
     * Returns the value of a property key
     * 
     * @param key The property key to retrieve
     * @return The value associated with the key, possibly overridden by an environment variable
     * @throws RuntimeException if the key is not found
     */
    public static String get(String key) {
    	
        // Get value from properties file
        String value = properties.getProperty(key);

        // Throw exception if the property key is missing
        if (value == null) {
            throw new RuntimeException("Property not found: " + key);
        }

        // Resolve environment variable overrides if present
        return resolveEnvVariables(value);
    }

    /**
     * Resolves environment variables in the format: ${ENV_VAR:default}
     * 
     * Example:
     *   base.url=${BASE_URL:http://localhost:8081}
     *   - If environment variable BASE_URL exists, its value is returned
     *   - Otherwise, "http://localhost:8081" (default) is returned
     * 
     * @param value The raw value from properties file
     * @return The resolved value, with environment variables substituted if available
     */
    private static String resolveEnvVariables(String value) {

        // If there is no ${...} syntax, return value as is
        if (!value.contains("${")) {
            return value;
        }

        // Extract the content inside ${...}
        int start = value.indexOf("${");
        int end = value.indexOf("}");

        String expression = value.substring(start + 2, end); // e.g., BASE_URL:http://localhost:8081
        String[] parts = expression.split(":", 2); // Split into env var and default value

        String envKey = parts[0]; // The environment variable name
        String defaultValue = parts.length > 1 ? parts[1] : ""; // Default value if env var not set

        // Get the environment variable
        String envValue = System.getenv(envKey);

        // Return env var value if exists, otherwise default
        return envValue != null ? envValue : defaultValue;
    }

}
