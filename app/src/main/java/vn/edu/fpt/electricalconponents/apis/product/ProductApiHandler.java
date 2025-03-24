package vn.edu.fpt.electricalconponents.apis.product;

import android.annotation.SuppressLint;
import android.content.Context;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import vn.edu.fpt.electricalconponents.apis.ApiListResponse;
import vn.edu.fpt.electricalconponents.apis.HttpClient;
import vn.edu.fpt.electricalconponents.apis.product.dto.CreateEditProductRequest;
import vn.edu.fpt.electricalconponents.models.Product;
import vn.edu.fpt.electricalconponents.state.StateManager;

public class ProductApiHandler {
    @SuppressLint("StaticFieldLeak")
    private static ProductApiHandler instance;
    private final String originalUrl;
    private final Context context;
    private final HttpClient httpClient;
    private ProductApi productApi;

    private ProductApiHandler(Context context) {
        originalUrl = "api/product/";
        this.context = context;
        httpClient = HttpClient.getInstance();
    }

    public static ProductApiHandler getInstance(Context context) {
        if (instance == null)
            instance = new ProductApiHandler(context);
        return instance;
    }

    public Observable<ApiListResponse<Product>> getAllProducts(int page, int size, String keyword,
                                                               int minPrice, int maxPrice,
                                                               String categoryId, String brandId, int order) {
        String accessToken = StateManager.getInstance(this.context).getAccessToken();
        httpClient.openClient(originalUrl, accessToken);
        productApi = httpClient.getClient().create(ProductApi.class);
        Observable<ApiListResponse<Product>> observable
                = productApi.getAllProducts(page, size, keyword, minPrice, maxPrice, categoryId, brandId, order)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(response -> {
                    if (response == null || response.getData() == null)
                        throw new NullPointerException("Get all products failed: Response body is empty");
                    if (response.getStatus() != 200)
                        throw new NullPointerException("Get all products failed: " + response.getMessage());
                    return response;
                });
        productApi = null;
        httpClient.closeClient();
        return observable;
    }

    public Observable<Product> getOneProduct(String id) {
        String accessToken = StateManager.getInstance(this.context).getAccessToken();
        httpClient.openClient(originalUrl, accessToken);
        productApi = httpClient.getClient().create(ProductApi.class);
        Observable<Product> observable
                = productApi.getOneProduct(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(response -> {
                    if (response == null || response.getData() == null)
                        throw new NullPointerException("Get one product failed: Response body is empty");
                    if (response.getStatus() != 200)
                        throw new NullPointerException("Get one product failed: " + response.getMessage());
                    return response.getData();
                });
        productApi = null;
        httpClient.closeClient();
        return observable;
    }

    public Observable<Product> createProduct(CreateEditProductRequest request) {
        String accessToken = StateManager.getInstance(this.context).getAccessToken();
        httpClient.openClient(originalUrl, accessToken);
        productApi = httpClient.getClient().create(ProductApi.class);
        Observable<Product> observable
                = productApi.createProduct(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(response -> {
                    if (response == null || response.getData() == null)
                        throw new NullPointerException("Create product failed: Response body is empty");
                    if (response.getStatus() != 200)
                        throw new NullPointerException("Create product failed: " + response.getMessage());
                    return response.getData();
                });
        productApi = null;
        httpClient.closeClient();
        return observable;
    }

    public Observable<Product> updateProduct(String id, CreateEditProductRequest request) {
        String accessToken = StateManager.getInstance(this.context).getAccessToken();
        httpClient.openClient(originalUrl, accessToken);
        productApi = httpClient.getClient().create(ProductApi.class);
        Observable<Product> observable
                = productApi.updateProduct(id, request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(response -> {
                    if (response == null || response.getData() == null)
                        throw new NullPointerException("Update product failed: Response body is empty");
                    if (response.getStatus() != 200)
                        throw new NullPointerException("Update product failed: " + response.getMessage());
                    return response.getData();
                });
        productApi = null;
        httpClient.closeClient();
        return observable;
    }

    public Observable<Product> deleteProduct(String id) {
        String accessToken = StateManager.getInstance(this.context).getAccessToken();
        httpClient.openClient(originalUrl, accessToken);
        productApi = httpClient.getClient().create(ProductApi.class);
        Observable<Product> observable
                = productApi.deleteProduct(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(response -> {
                    if (response == null || response.getData() == null)
                        throw new NullPointerException("Delete product failed: Response body is empty");
                    if (response.getStatus() != 200)
                        throw new NullPointerException("Delete product failed: " + response.getMessage());
                    return response.getData();
                });
        productApi = null;
        httpClient.closeClient();
        return observable;
    }
}
