package vn.edu.fpt.electricalconponents.utils;

import android.util.Log;

import java.io.File;

import io.github.cdimascio.dotenv.Dotenv;

/**
 * The EnvConfiguration class provides a singleton interface for accessing environment variables
 * loaded from a .env file.
 * <p>
 * This class utilizes the `dotenv` library to read environment variables from a .env file
 * and makes them accessible throughout the application. It employs the Singleton design
 * pattern to ensure that only one instance of EnvConfiguration exists.
 * <p>
 * Usage:
 * <pre>
 *     // Get the singleton instance
 *     EnvConfiguration envConfig = EnvConfiguration.getInstance();
 *
 *     // Retrieve an environment variable
 *     String databaseUrl = envConfig.getKey("DATABASE_URL");
 * </pre>
 */
public final class EnvConfiguration {
    private static EnvConfiguration instance;
    private final Dotenv config;

    private EnvConfiguration() {
        File file = new File("\\.env");
        Log.d("ENV_PATH", "File exists: " + file.exists());
        config = Dotenv.configure()
                .directory("/src")
                .filename(".env")
                .load();
    }

    /**
     * Returns the singleton instance of the EnvConfiguration class.
     * <p>
     * This method implements the Singleton design pattern, ensuring that only one
     * instance of EnvConfiguration exists throughout the application's lifecycle.
     * If an instance has not been created yet, it creates one and stores it.
     * Subsequent calls to this method will return the same instance.
     *
     * @return The singleton instance of EnvConfiguration.
     */
    public static EnvConfiguration getInstance() {
        if (instance == null) {
            instance = new EnvConfiguration();
        }
        return instance;
    }

    /**
     * Retrieves the value associated with the specified key from the configuration.
     *
     * @param key The key whose associated value is to be retrieved.
     * @return The value to which the specified key is mapped, or {@code null} if this configuration contains no mapping for the key.
     * @throws NullPointerException if the specified key is null.
     */
    public String getKey(String key) {
        return config.get(key);
    }
}
