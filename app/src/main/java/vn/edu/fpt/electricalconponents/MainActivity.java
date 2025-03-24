package vn.edu.fpt.electricalconponents;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import vn.edu.fpt.electricalconponents.activities.LoginActivity;
import vn.edu.fpt.electricalconponents.state.StateManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        Context applicationContext = MyApplication.getInstance();
        String accessToken = StateManager.getInstance(applicationContext).getAccessToken();
        if (accessToken == null) {
            Intent loginIntent = new Intent(this, LoginActivity.class);
            startActivity(loginIntent);
        }
    }
}