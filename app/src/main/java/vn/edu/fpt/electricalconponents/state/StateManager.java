package vn.edu.fpt.electricalconponents.state;

import android.content.Context;
import android.content.SharedPreferences;

import lombok.Getter;

public class StateManager {
    private static final String PREF_NAME = "AppPreps";
    private static final String KEY_ACCESS_TOKEN = "access_token";
    private static final String KEY_REFRESH_TOKEN = "refresh_token";
    private static final String KEY_USER_ID = "user_id";
    private static final String KEY_USER_ROLE = "user_role";
    private static StateManager instance;
    private final SharedPreferences sharedPreferences;
    @Getter
    private String accessToken;
    @Getter
    private String refreshToken;
    @Getter
    private String userId;
    @Getter
    private String userRole;

    private StateManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        accessToken = sharedPreferences.getString(KEY_ACCESS_TOKEN, null);
        refreshToken = sharedPreferences.getString(KEY_REFRESH_TOKEN, null);
        userId = sharedPreferences.getString(KEY_USER_ID, null);
        userRole = sharedPreferences.getString(KEY_USER_ROLE, null);
    }

    public static synchronized StateManager getInstance(Context context) {
        if (instance == null) {
            instance = new StateManager(context);
        }
        return instance;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
        sharedPreferences.edit().putString(KEY_ACCESS_TOKEN, accessToken).apply();
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
        sharedPreferences.edit().putString(KEY_REFRESH_TOKEN, refreshToken).apply();
    }

    public void setUserId(String userId) {
        this.userId = userId;
        sharedPreferences.edit().putString(KEY_USER_ID, userId).apply();
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
        sharedPreferences.edit().putString(KEY_USER_ROLE, userRole).apply();
    }
}
