package vn.edu.fpt.electricalconponents.apis.user;

import android.annotation.SuppressLint;
import android.content.Context;

import java.util.List;
import java.util.MissingFormatArgumentException;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import vn.edu.fpt.electricalconponents.apis.HttpClient;
import vn.edu.fpt.electricalconponents.apis.user.dto.UpdateUserRequest;
import vn.edu.fpt.electricalconponents.models.User;
import vn.edu.fpt.electricalconponents.state.StateManager;

public class UserApiHandler {
    @SuppressLint("StaticFieldLeak")
    private static UserApiHandler instance;
    private final String originalUrl;
    private final Context context;
    private HttpClient httpClient;
    private UserApi userApi;

    private UserApiHandler(Context context) {
        originalUrl = "api/user/";
        this.context = context;
    }

    public static UserApiHandler getInstance(Context context) {
        if (instance == null) {
            instance = new UserApiHandler(context);
        }
        return instance;
    }

    public Observable<List<User>> getAllUsers(int page, int size, String keyword) {
        String accessToken = StateManager.getInstance(this.context).getAccessToken();
        httpClient.openClient(originalUrl, accessToken);
        userApi = httpClient.getClient().create(UserApi.class);
        Observable<List<User>> observable
                = userApi.getAllUsers(page, size, keyword)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(response -> {
                    if (response == null || response.getData() == null)
                        throw new MissingFormatArgumentException("Get all users failed: Response body is empty");
                    if (response.getStatus() != 200)
                        throw new MissingFormatArgumentException("Get all users failed: " + response.getMessage());
                    return response.getData();
                });
        userApi = null;
        httpClient.closeClient();
        return observable;
    }

    public Observable<User> getOneUser(String id) {
        String accessToken = StateManager.getInstance(this.context).getAccessToken();
        httpClient.openClient(originalUrl, accessToken);
        userApi = httpClient.getClient().create(UserApi.class);
        Observable<User> observable
                = userApi.getOneUser(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(response -> {
                    if (response == null || response.getData() == null)
                        throw new MissingFormatArgumentException("Get one user failed: Response body is empty");
                    if (response.getStatus() != 200)
                        throw new MissingFormatArgumentException("Get one user failed: " + response.getMessage());
                    return response.getData();
                });
        userApi = null;
        httpClient.closeClient();
        return observable;
    }

    public Observable<User> updateUser(String id, UpdateUserRequest user) {
        String accessToken = StateManager.getInstance(this.context).getAccessToken();
        httpClient.openClient(originalUrl, accessToken);
        userApi = httpClient.getClient().create(UserApi.class);
        Observable<User> observable
                = userApi.updateUser(id, user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(response -> {
                    if (response == null || response.getData() == null)
                        throw new MissingFormatArgumentException("Get one user failed: Response body is empty");
                    if (response.getStatus() != 200)
                        throw new MissingFormatArgumentException("Get one user failed: " + response.getMessage());
                    return response.getData();
                });
        userApi = null;
        httpClient.closeClient();
        return observable;
    }

    public Observable<User> deleteUser(String id) {
        String accessToken = StateManager.getInstance(this.context).getAccessToken();
        httpClient.openClient(originalUrl, accessToken);
        userApi = httpClient.getClient().create(UserApi.class);
        Observable<User> observable
                = userApi.deleteUser(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(response -> {
                    if (response == null || response.getData() == null)
                        throw new MissingFormatArgumentException("Get one user failed: Response body is empty");
                    if (response.getStatus() != 200)
                        throw new MissingFormatArgumentException("Get one user failed: " + response.getMessage());
                    return response.getData();
                });
        userApi = null;
        httpClient.closeClient();
        return observable;
    }
}
