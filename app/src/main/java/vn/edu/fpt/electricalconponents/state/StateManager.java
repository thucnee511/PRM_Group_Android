package vn.edu.fpt.electricalconponents.state;

import android.content.Context;
import android.content.SharedPreferences;

public class StateManager {
    private static final String PREF_NAME = "AppPreps";
    private static final String KEY_USER_ID = "user_id";
    private static final String KEY_ACCESS_TOKEN = "access_token";
    private static final String KEY_REFRESH_TOKEN = "refresh_token";
    private static StateManager instance;
    private final SharedPreferences sharedPreferences;
    private String accessToken;
    private String refreshToken;

    private StateManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        accessToken = sharedPreferences.getString(KEY_ACCESS_TOKEN, null);
        refreshToken = sharedPreferences.getString(KEY_REFRESH_TOKEN, null);
    }

    public static synchronized StateManager getInstance(Context context) {
        if (instance == null) {
            instance = new StateManager(context);
        }
        return instance;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
        sharedPreferences.edit().putString(KEY_ACCESS_TOKEN, accessToken).apply();
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
        sharedPreferences.edit().putString(KEY_REFRESH_TOKEN, refreshToken).apply();
    }
}
