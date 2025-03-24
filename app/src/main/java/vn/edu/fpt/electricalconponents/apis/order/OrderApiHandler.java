package vn.edu.fpt.electricalconponents.apis.order;

import android.annotation.SuppressLint;
import android.content.Context;

import java.time.LocalDateTime;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import vn.edu.fpt.electricalconponents.apis.ApiItemResponse;
import vn.edu.fpt.electricalconponents.apis.ApiListResponse;
import vn.edu.fpt.electricalconponents.apis.HttpClient;
import vn.edu.fpt.electricalconponents.models.Order;
import vn.edu.fpt.electricalconponents.models.OrderItem;
import vn.edu.fpt.electricalconponents.state.StateManager;

public class OrderApiHandler {
    @SuppressLint("StaticFieldLeak")
    private static OrderApiHandler instance;
    private final String originalUrl;
    private final Context context;
    private final HttpClient httpClient;
    private OrderApi orderApi;

    private OrderApiHandler(Context context) {
        originalUrl = "api/order/";
        this.context = context;
        httpClient = HttpClient.getInstance();
    }

    public static OrderApiHandler getInstance(Context context) {
        if (instance == null)
            instance = new OrderApiHandler(context);
        return instance;
    }

    public Observable<ApiListResponse<Order>> getAllOrders(int page, int size, String userId,
                                                           LocalDateTime fromDate, LocalDateTime toDate,
                                                           String orderType, String productId) {
        String accessToken = StateManager.getInstance(this.context).getAccessToken();
        httpClient.openClient(originalUrl, accessToken);
        orderApi = httpClient.getClient().create(OrderApi.class);
        Observable<ApiListResponse<Order>> observable
                = orderApi.getAllOrders(page, size, userId, fromDate, toDate, orderType, productId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(response -> {
                    if (response == null || response.getData() == null)
                        throw new NullPointerException("Get all orders failed: Response body is empty");
                    if (response.getStatus() != 200)
                        throw new NullPointerException("Get all orders failed: " + response.getMessage());
                    return response;
                });
        orderApi = null;
        httpClient.closeClient();
        return observable;
    }

    public Observable<ApiItemResponse<OrderItem>> getOneOrder(String id) {
        String accessToken = StateManager.getInstance(this.context).getAccessToken();
        httpClient.openClient(originalUrl, accessToken);
        orderApi = httpClient.getClient().create(OrderApi.class);
        Observable<ApiItemResponse<OrderItem>> observable
                = orderApi.getOneOrder(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(response -> {
                    if (response == null || response.getData() == null)
                        throw new NullPointerException("Get one order failed: Response body is empty");
                    if (response.getStatus() != 200)
                        throw new NullPointerException("Get one order failed: " + response.getMessage());
                    return response;
                });
        orderApi = null;
        httpClient.closeClient();
        return observable;
    }

    public Observable<ApiItemResponse<Order>> createOrder(String cartId) {
        String accessToken = StateManager.getInstance(this.context).getAccessToken();
        httpClient.openClient(originalUrl, accessToken);
        orderApi = httpClient.getClient().create(OrderApi.class);
        Observable<ApiItemResponse<Order>> observable
                = orderApi.createOrder(cartId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(response -> {
                    if (response == null || response.getData() == null)
                        throw new NullPointerException("Create order failed: Response body is empty");
                    if (response.getStatus() != 200)
                        throw new NullPointerException("Create order failed: " + response.getMessage());
                    return response;
                });
        orderApi = null;
        httpClient.closeClient();
        return observable;
    }
}
