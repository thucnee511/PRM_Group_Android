package vn.edu.fpt.electricalconponents.apis;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.Interceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import vn.edu.fpt.electricalconponents.utils.EnvConfiguration;

/**
 * The HttpClient class provides a singleton instance for interacting with a remote API via Retrofit.
 * It handles the configuration and initialization of the API URL, including optional authorization,
 * based on environment variables and provided parameters.
 * <p>
 * This class implements the Singleton design pattern, ensuring that only one instance of the
 * HttpClient is created throughout the application's lifecycle, providing a central point for
 * API interactions. It also manages the lifecycle of the underlying Retrofit client.
 */
public final class HttpClient {
    private static HttpClient instance;
    private final String API_URL;
    private Retrofit retrofit;

    private HttpClient() {
        EnvConfiguration config = EnvConfiguration.getInstance();
        API_URL = config.getKey("API_URL");
    }

    /**
     * Returns the singleton instance of the HttpClient.
     * <p>
     * This method implements the Singleton design pattern to ensure that only one
     * instance of HttpClient exists throughout the application's lifecycle.
     * It lazily initializes the instance on the first call to this method.
     * Subsequent calls will return the already created instance.
     *
     * @return The singleton instance of the HttpClient.
     */
    public static HttpClient getInstance() {
        if (instance == null) {
            instance = new HttpClient();
        }
        return instance;
    }

    /**
     * Initializes the Retrofit client for making API calls.
     * <p>
     * This method configures and builds a Retrofit instance, including setting up:
     * - A base URL constructed from the provided `originalUrl`.
     * - A Gson converter factory for parsing JSON responses.
     * - Date format for handling date/time values.
     *
     * @param originalUrl The relative path to be appended to the base API URL (API_URL) to form the full base URL for the API requests.
     *                    For example, if API_URL is "https://api.example.com/" and originalUrl is "/v1/",
     *                    the effective base URL will be "https://api.example.com/v1/".
     * @throws IllegalStateException If the Retrofit instance has already been initialized.
     *                               This indicates that this method should not be called more than once without resetting the instance.
     */
    public void openClient(String originalUrl) {
        if (retrofit != null) throw new IllegalStateException("Retrofit already initialized");
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();
        retrofit = new Retrofit.Builder().baseUrl(API_URL + originalUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }
    
    /**
     * Initializes the Retrofit client for making API calls.
     * <p>
     * This method configures and builds a Retrofit instance, including setting up:
     * - A base URL constructed from the provided `originalUrl`.
     * - An OkHttpClient with an interceptor that adds a Bearer token to the Authorization header of each request.
     * - A Gson converter factory for parsing JSON responses.
     * - Date format for handling date/time values.
     *
     * @param originalUrl The relative path to be appended to the base API URL (API_URL) to form the full base URL for the API requests.
     *                    For example, if API_URL is "https://api.example.com/" and originalUrl is "/v1/",
     *                    the effective base URL will be "https://api.example.com/v1/".
     * @param bearerToken The Bearer token to be included in the Authorization header of each API request.
     * @throws IllegalStateException If the Retrofit instance has already been initialized.
     *                               This indicates that this method should not be called more than once without resetting the instance.
     */
    public void openClient(String originalUrl, String bearerToken) {
        if (retrofit != null) throw new IllegalStateException("Retrofit already initialized");

        okhttp3.OkHttpClient okHttpClient = new okhttp3.OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @NonNull
                    @Override
                    public okhttp3.Response intercept(@NonNull Chain chain) throws IOException {
                        okhttp3.Request originalRequest = chain.request();
                        okhttp3.Request.Builder builder = originalRequest.newBuilder()
                                .header("Authorization", "Bearer " + bearerToken);
                        okhttp3.Request newRequest = builder.build();
                        return chain.proceed(newRequest);
                    }
                })
                .build();
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();
        retrofit = new Retrofit.Builder().baseUrl(API_URL + originalUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    /**
     * Provides a Retrofit client instance.
     */
    public Retrofit getClient() {
        if (retrofit == null) throw new IllegalStateException("Retrofit not initialized");
        return retrofit;
    }

    /**
     * Closes the current Retrofit client instance.
     *
     * <p>This method sets the internal Retrofit client instance to null.  After calling this method,
     * any attempt to make requests through this client will result in an {@link IllegalStateException}
     * until a new client is initialized.</p>
     *
     * <p>It's crucial to ensure that no ongoing or pending requests are using the client before
     * calling this method.  While the method itself does not explicitly cancel requests, setting
     * `retrofit` to null will effectively prevent new requests from being created and will cause errors
     * if existing requests try to use it after being invalidated.</p>
     *
     * @throws IllegalStateException if the Retrofit client has not been initialized.
     *                               This means the `retrofit` instance is null when this method is called, which is unexpected.
     */
    public void closeClient() {
        if (retrofit == null) throw new IllegalStateException("Retrofit not initialized");
        retrofit = null;
    }
}
