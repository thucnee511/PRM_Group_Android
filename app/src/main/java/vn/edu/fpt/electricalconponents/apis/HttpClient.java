package vn.edu.fpt.electricalconponents.apis;

import androidx.annotation.NonNull;

import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.Interceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import vn.edu.fpt.electricalconponents.utils.EnvConfiguration;

/**
 * The HttpClient class provides a singleton instance for interacting with a remote API.
 * It handles the configuration and initialization of the API URL based on environment variables.
 * <p>
 * This class ensures that only one instance of the HttpClient is created throughout the application's lifecycle.
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
    public static HttpClient getInstance(){
        if(instance == null){
            instance = new HttpClient();
        }
        return instance;
    }


    /**
     * Initializes and configures the Retrofit client for making network requests.
     *
     * <p>This method performs the following actions:</p>
     * <ul>
     *   <li>Checks if a Retrofit instance already exists. If so, it throws an {@link IllegalStateException}
     *       to prevent re-initialization.</li>
     *   <li>Creates a {@link Gson} instance with a specific date format ("yyyy-MM-dd'T'HH:mm:ssZ")
     *       to handle date/time serialization and deserialization.</li>
     *   <li>Builds a {@link Retrofit} client using the specified base URL ({@code API_URL}).</li>
     *   <li>Configures Retrofit to use {@link GsonConverterFactory} for converting JSON responses
     *       to Java objects and vice-versa.</li>
     * </ul>
     *
     * <p><b>Preconditions:</b></p>
     * <ul>
     *   <li>{@code API_URL} must be a valid base URL for the API.</li>
     *   <li>Retrofit instance should not have been initialized before calling this method.</li>
     * </ul>
     *
     * <p><b>Postconditions:</b></p>
     * <ul>
     *   <li>A new Retrofit instance is created and stored in the {@code retrofit} field.</li>
     *   <li>The Retrofit instance is configured to use Gson for JSON conversion.</li>
     *   <li>The Retrofit instance is ready to be used for creating API service interfaces.</li>
     * </ul>
     *
     * @throws IllegalStateException if the Retrofit instance has already been initialized.
     */
    public void openClient(){
        if (retrofit != null) throw new IllegalStateException("Retrofit already initialized");
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();
        retrofit = new Retrofit.Builder().baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    /**
     * Initializes and configures the Retrofit client for making API requests.
     * This method sets up an OkHttpClient with an interceptor to add the
     * provided Bearer token to the Authorization header of each request.
     * It also configures Gson for JSON serialization/deserialization and sets the
     * date format. Finally, it creates a Retrofit instance with the specified
     * base URL, OkHttpClient, and Gson converter.
     *
     * @param bearerToken The Bearer token to be included in the Authorization header of API requests.
     *                    This token is used for authentication and authorization.
     * @throws IllegalStateException If the Retrofit client has already been initialized.
     */
    public void openClient(String bearerToken) {
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
        retrofit = new Retrofit.Builder().baseUrl(API_URL).client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson)).build();
    }
    /**
     * Provides a Retrofit client instance.
     */
    public Retrofit getClient(){
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
     *         This means the `retrofit` instance is null when this method is called, which is unexpected.
     */
    public void closeClient(){
        if(retrofit == null) throw new IllegalStateException("Retrofit not initialized");
        retrofit = null;
    }
}
