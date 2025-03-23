package vn.edu.fpt.electricalconponents.apis.authentication;


import android.content.Context;

import java.util.MissingFormatArgumentException;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import vn.edu.fpt.electricalconponents.apis.HttpClient;
import vn.edu.fpt.electricalconponents.apis.authentication.dto.RefreshRequest;
import vn.edu.fpt.electricalconponents.apis.authentication.dto.SignIgnRequest;
import vn.edu.fpt.electricalconponents.apis.authentication.dto.SignUpRequest;
import vn.edu.fpt.electricalconponents.apis.authentication.dto.Token;
import vn.edu.fpt.electricalconponents.models.User;
import vn.edu.fpt.electricalconponents.state.StateManager;

public final class AuthenticationApiHandler {
    private static AuthenticationApiHandler instance;
    private final String originalUrl;
    private final Context context;
    private HttpClient httpClient;
    private AuthenticationApi authenticationApi;

    private AuthenticationApiHandler(Context context) {
        originalUrl = "api/auth/";
        this.context = context;
    }

    public static AuthenticationApiHandler getInstance(Context context) {
        if (instance == null) {
            instance = new AuthenticationApiHandler(context);
        }
        return instance;
    }

    public Observable<Token> signIn(SignIgnRequest request) {
        httpClient.openClient(originalUrl);
        authenticationApi = httpClient.getClient().create(AuthenticationApi.class);
        Observable<Token> observable
                = authenticationApi.signIn(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(response -> {
                    if (response == null || response.getData() == null)
                        throw new MissingFormatArgumentException("Login failed: Response body is empty");
                    if (response.getStatus() != 200)
                        throw new MissingFormatArgumentException("Login failed: " + response.getMessage());
                    return response.getData();
                });
        authenticationApi = null;
        httpClient.closeClient();
        return observable;
    }

    public Observable<Token> signUp(SignUpRequest request) {
        httpClient.openClient(originalUrl);
        authenticationApi = httpClient.getClient().create(AuthenticationApi.class);
        Observable<Token> observable
                = authenticationApi.signUp(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(response -> {
                    if (response == null || response.getData() == null)
                        throw new MissingFormatArgumentException("Register failed: Response body is empty");
                    if (response.getStatus() != 200)
                        throw new MissingFormatArgumentException("Register failed: " + response.getMessage());
                    return response.getData();
                });
        authenticationApi = null;
        httpClient.closeClient();
        return observable;
    }

    public Observable<Token> refresh() {
        Token currentToken = new Token(
                StateManager.getInstance(this.context).getAccessToken(),
                StateManager.getInstance(this.context).getRefreshToken()
        );
        httpClient.openClient(originalUrl, currentToken.getAccessToken());
        authenticationApi = httpClient.getClient().create(AuthenticationApi.class);
        Observable<Token> observable
                = authenticationApi.refresh(new RefreshRequest(currentToken.getRefreshToken()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(response -> {
                    if (response == null || response.getData() == null)
                        throw new MissingFormatArgumentException("Refresh token failed: Response body is empty");
                    if (response.getStatus() != 200)
                        throw new MissingFormatArgumentException("Refresh token failed: " + response.getMessage());
                    return response.getData();
                });
        authenticationApi = null;
        httpClient.closeClient();
        return observable;
    }

    public Observable<User> getMe() {
        String accessToken = StateManager.getInstance(this.context).getAccessToken();
        httpClient.openClient(originalUrl, accessToken);
        authenticationApi = httpClient.getClient().create(AuthenticationApi.class);
        Observable<User> observable
                = authenticationApi.getMe()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(response -> {
                    if (response == null || response.getData() == null)
                        throw new MissingFormatArgumentException("Get me failed: Response body is empty");
                    if (response.getStatus() != 200)
                        throw new MissingFormatArgumentException("Get me failed: " + response.getMessage());
                    return response.getData();
                });
        authenticationApi = null;
        httpClient.closeClient();
        return observable;
    }
}
