package vn.edu.fpt.electricalconponents.apis.category;

import android.annotation.SuppressLint;
import android.content.Context;

import vn.edu.fpt.electricalconponents.apis.HttpClient;

public class CategoryApiHandler {
    @SuppressLint("StaticFieldLeak")
    private static CategoryApiHandler instance;
    private final String originalUrl;
    private final Context context;
    private HttpClient httpClient;
    private CategoryApi categoryApi;
    private CategoryApiHandler(Context context) {
        originalUrl = "api/category/";
        this.context = context;
    }
    public static CategoryApiHandler getInstance(Context context) {
        if (instance == null) {
            instance = new CategoryApiHandler(context);
        }
        return instance;
}
