package vn.edu.fpt.electricalconponents.apis;

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
     * Initializes the Retrofit client for making network requests.
     * <p>
     * This method creates a new Retrofit instance configured with the base API URL
     * and the Gson converter factory. It ensures that only one instance of Retrofit
     * is created by throwing an IllegalStateException if the client has already
     * been initialized.
     *
     * @throws IllegalStateException if the Retrofit client has already been initialized.
     *                               This indicates that `openClient()` has been called
     *                               previously and the client is already set up.
     */
    public void openClient(){
        if (retrofit != null) throw new IllegalStateException("Retrofit already initialized");
        retrofit = new Retrofit.Builder().baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
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
