package vn.edu.fpt.electricalconponents.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.credentials.CredentialManager;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import vn.edu.fpt.electricalconponents.MainActivity;
import vn.edu.fpt.electricalconponents.MyApplication;
import vn.edu.fpt.electricalconponents.R;
import vn.edu.fpt.electricalconponents.apis.authentication.AuthenticationApiHandler;
import vn.edu.fpt.electricalconponents.apis.authentication.dto.GoogleSignInRequest;
import vn.edu.fpt.electricalconponents.apis.authentication.dto.SignInRequest;
import vn.edu.fpt.electricalconponents.state.StateManager;

public class LoginActivity extends AppCompatActivity {
    private static final int RC_SIGN_IN = 9001;
    private FirebaseAuth mAuth;
    private CredentialManager credentialManager;
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
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        btnSignIn = findViewById(R.id.btnSignIn);
        btnSignUp = findViewById(R.id.btnSignUp);
        btnSignInGoogle = findViewById(R.id.btnSignInGoogle);
        mAuth = FirebaseAuth.getInstance();
        credentialManager = CredentialManager.create(getBaseContext());

        btnSignInGoogle.setOnClickListener(v -> onClickBtnSignInGoogle());
        btnSignIn.setOnClickListener(v -> onClickBtnSignIn());
    }

    @SuppressLint("CheckResult")
    private void onClickBtnSignIn() {
        String username = edtEmail.getText().toString();
        String password = edtPassword.getText().toString();
        SignInRequest request = new SignInRequest(username, password);
        AuthenticationApiHandler.getInstance(MyApplication.getInstance())
                .signIn(request)
                .subscribe(token -> {
                    StateManager.getInstance(MyApplication.getInstance()).setAccessToken(token.getAccessToken());
                    StateManager.getInstance(MyApplication.getInstance()).setRefreshToken(token.getRefreshToken());
                    Toast.makeText(this, "Sign in via Google successfully", Toast.LENGTH_LONG).show();
                    Intent mainActivity = new Intent(this, MainActivity.class);
                    startActivity(mainActivity);
                });
    }

    private void validateLogin(String email, String password) {
        
    }

    private void onClickBtnSignUp() {

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
                // Gửi token này đến server backend của bạn
            } catch (ApiException e) {
                Toast.makeText(this, "Sign in via Google failed: ", Toast.LENGTH_LONG).show();
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