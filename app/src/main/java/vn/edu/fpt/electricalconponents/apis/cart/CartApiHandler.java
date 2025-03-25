package vn.edu.fpt.electricalconponents.apis.cart;

import android.annotation.SuppressLint;
import android.content.Context;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import vn.edu.fpt.electricalconponents.apis.ApiItemResponse;
import vn.edu.fpt.electricalconponents.apis.ApiListResponse;
import vn.edu.fpt.electricalconponents.apis.HttpClient;
import vn.edu.fpt.electricalconponents.models.Cart;
import vn.edu.fpt.electricalconponents.models.CartItem;
import vn.edu.fpt.electricalconponents.state.StateManager;

public class CartApiHandler {
    @SuppressLint("StaticFieldLeak")
    private static CartApiHandler instance;
    private final String originalUrl;
    private final Context context;
    private final HttpClient httpClient;
    private CartApi cartApi;

    private CartApiHandler(Context context) {
        originalUrl = "api/";
        this.context = context;
        httpClient = HttpClient.getInstance();
    }

    public static CartApiHandler getInstance(Context context) {
        if (instance == null)
            instance = new CartApiHandler(context);
        return instance;
    }

    public Observable<Cart> getCart() {
        String accessToken = StateManager.getInstance(this.context).getAccessToken();
        httpClient.openClient(originalUrl, accessToken);
        cartApi = httpClient.getClient().create(CartApi.class);
        Observable<Cart> observable
                = cartApi.getCarts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(response -> {
                    System.out.println(response);
                    if (response == null || response.getData() == null)
                        throw new NullPointerException("Get all carts failed: Response body is empty");
                    if (response.getStatus() != 200)
                        throw new NullPointerException("Get all carts failed: " + response.getMessage());
                    return response.getData();
                });
        cartApi = null;
        httpClient.closeClient();
        return observable;
    }

    public Observable<ApiListResponse<CartItem>> getCartItems(String id) {
        String accessToken = StateManager.getInstance(this.context).getAccessToken();
        httpClient.openClient(originalUrl, accessToken);
        cartApi = httpClient.getClient().create(CartApi.class);
        Observable<ApiListResponse<CartItem>> observable
                = cartApi.getCartItems(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(response -> {
                    if (response == null || response.getData() == null)
                        throw new NullPointerException("Get all cart items failed: Response body is empty");
                    if (response.getStatus() != 200)
                        throw new NullPointerException("Get all cart items failed: " + response.getMessage());
                    return response;
                });
        cartApi = null;
        httpClient.closeClient();
        return observable;
    }

    public Observable<ApiItemResponse<CartItem>> createCart(String id, String productId, int quantity) {
        String accessToken = StateManager.getInstance(this.context).getAccessToken();
        httpClient.openClient(originalUrl, accessToken);
        cartApi = httpClient.getClient().create(CartApi.class);
        Observable<ApiItemResponse<CartItem>> observable
                = cartApi.createCart(id, productId, quantity)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(response -> {
                    if (response == null || response.getData() == null)
                        throw new NullPointerException("Create cart failed: Response body is empty");
                    if (response.getStatus() != 200)
                        throw new NullPointerException("Create cart failed: " + response.getMessage());
                    return response;
                });
        cartApi = null;
        httpClient.closeClient();
        return observable;
    }

    public Observable<ApiItemResponse<CartItem>> updateCart(String id, String productId, int quantity) {
        String accessToken = StateManager.getInstance(this.context).getAccessToken();
        httpClient.openClient(originalUrl, accessToken);
        cartApi = httpClient.getClient().create(CartApi.class);
        Observable<ApiItemResponse<CartItem>> observable
                = cartApi.updateCart(id, productId, quantity)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(response -> {
                    if (response == null || response.getData() == null)
                        throw new NullPointerException("Update cart failed: Response body is empty");
                    if (response.getStatus() != 200)
                        throw new NullPointerException("Update cart failed: " + response.getMessage());
                    return response;
                });
        cartApi = null;
        httpClient.closeClient();
        return observable;
    }

    public Observable<ApiItemResponse<CartItem>> deleteCart(String id, String productId) {
        String accessToken = StateManager.getInstance(this.context).getAccessToken();
        httpClient.openClient(originalUrl, accessToken);
        cartApi = httpClient.getClient().create(CartApi.class);
        Observable<ApiItemResponse<CartItem>> observable
                = cartApi.deleteCart(id, productId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(response -> {
                    if (response == null || response.getData() == null)
                        throw new NullPointerException("Delete cart failed: Response body is empty");
                    if (response.getStatus() != 200)
                        throw new NullPointerException("Delete cart failed: " + response.getMessage());
                    return response;
                });
        cartApi = null;
        httpClient.closeClient();
        return observable;
    }
}
