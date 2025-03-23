package vn.edu.fpt.electricalconponents.apis.category;

import android.annotation.SuppressLint;

import java.util.List;
import java.util.MissingFormatArgumentException;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import vn.edu.fpt.electricalconponents.apis.HttpClient;
import vn.edu.fpt.electricalconponents.models.Category;

public class CategoryApiHandler {
    @SuppressLint("StaticFieldLeak")
    private static CategoryApiHandler instance;
    private final String originalUrl;
    private HttpClient httpClient;
    private CategoryApi categoryApi;

    private CategoryApiHandler() {
        originalUrl = "api/category/";
    }

    public static CategoryApiHandler getInstance() {
        if (instance == null) {
            instance = new CategoryApiHandler();
        }
        return instance;
    }

    public Observable<List<Category>> getAllCategories() {
        httpClient.openClient(originalUrl);
        categoryApi = httpClient.getClient().create(CategoryApi.class);
        Observable<List<Category>> observable
                = categoryApi.getAllCategories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(response -> {
                    if (response == null || response.getData() == null)
                        throw new MissingFormatArgumentException("Get all categories failed: Response body is empty");
                    if (response.getStatus() != 200)
                        throw new MissingFormatArgumentException("Get all categories failed: " + response.getMessage());
                    return response.getData();
                });
        categoryApi = null;
        httpClient.closeClient();
        return observable;
    }
}
