package vn.edu.fpt.electricalconponents.activities.auth;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import vn.edu.fpt.electricalconponents.MainActivity;
import vn.edu.fpt.electricalconponents.MyApplication;
import vn.edu.fpt.electricalconponents.R;
import vn.edu.fpt.electricalconponents.apis.authentication.AuthenticationApiHandler;
import vn.edu.fpt.electricalconponents.apis.authentication.dto.GoogleSignInRequest;
import vn.edu.fpt.electricalconponents.apis.authentication.dto.SignInRequest;
import vn.edu.fpt.electricalconponents.state.StateManager;

public class LoginActivity extends AppCompatActivity {
    private static final int RC_SIGN_IN = 9001;
    private TextView txtError;
    private EditText edtEmail;
    private EditText edtPassword;
    private Button btnSignIn;
    private Button btnSignUp;
    private Button btnSignInGoogle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        txtError = findViewById(R.id.loginAtv_txtError);
        edtEmail = findViewById(R.id.loginAtv_edtEmail);
        edtPassword = findViewById(R.id.loginAtv_edtPassword);
        btnSignIn = findViewById(R.id.loginAtv_btnSignIn);
        btnSignUp = findViewById(R.id.loginAtv_btnSignUp);
        btnSignInGoogle = findViewById(R.id.loginAtv_btnSignInGoogle);

        btnSignInGoogle.setOnClickListener(v -> onClickBtnSignInGoogle());
        btnSignIn.setOnClickListener(v -> onClickBtnSignIn());
        btnSignUp.setOnClickListener(v -> onClickBtnSignUp());
    }

    @SuppressLint("CheckResult")
    private void onClickBtnSignIn() {
        String email = edtEmail.getText().toString();
        String password = edtPassword.getText().toString();
        try {
            validateLogin(email, password);
            SignInRequest request = new SignInRequest(email, password);
            AuthenticationApiHandler.getInstance(MyApplication.getInstance())
                    .signIn(request)
                    .subscribe(token -> {
                        StateManager.getInstance(MyApplication.getInstance()).setAccessToken(token.getAccessToken());
                        StateManager.getInstance(MyApplication.getInstance()).setRefreshToken(token.getRefreshToken());
                        Toast.makeText(this, "Sign in via Google successfully", Toast.LENGTH_LONG).show();
                        Intent mainActivity = new Intent(this, MainActivity.class);
                        startActivity(mainActivity);
                    });
        } catch (Exception e) {
            txtError.setText(e.getMessage());
        }
    }

    private void validateLogin(String email, String password) {
        final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$";
        boolean isEmailValid = email.matches(EMAIL_REGEX);
        boolean isPasswordValid = password.matches(PASSWORD_REGEX);
        if (!isEmailValid) {
            throw new IllegalArgumentException("Invalid email format");
        } else if (!isPasswordValid) {
            throw new IllegalArgumentException("Password must contain at least one small letter," +
                    " one capital letter," +
                    " one special character," +
                    " one digit " +
                    " and minimum length of 8 characters");
        }
    }

    private void onClickBtnSignUp() {
        Intent registerActivity = new Intent(this, RegisterActivity.class);
        startActivity(registerActivity);
    }

    private void onClickBtnSignInGoogle() {
        Log.w("SignIn", "Google sign in: " + R.string.default_web_client_id);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        GoogleSignInClient googleSignInClient = GoogleSignIn.getClient(this, gso);
        Intent signInIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                GoogleSignInRequest request = new GoogleSignInRequest(
                        account.getEmail(),
                        account.getDisplayName(),
                        null
                );
                handlerGoogleSignIn(request);
            } catch (ApiException e) {
                txtError.setText(String.format("Login error: %s", e.getMessage()));
            }
        }
    }

    @SuppressLint("CheckResult")
    private void handlerGoogleSignIn(GoogleSignInRequest request) {
        AuthenticationApiHandler.getInstance(MyApplication.getInstance())
                .googleSignIn(request)
                .subscribe(token -> {
                    StateManager.getInstance(MyApplication.getInstance()).setAccessToken(token.getAccessToken());
                    StateManager.getInstance(MyApplication.getInstance()).setRefreshToken(token.getRefreshToken());
                    Toast.makeText(this, "Sign in via Google successfully", Toast.LENGTH_LONG).show();
                    Intent mainActivity = new Intent(this, MainActivity.class);
                    startActivity(mainActivity);
                }, error -> {
                    Toast.makeText(this, error.getMessage(), Toast.LENGTH_LONG).show();
                });
    }
}