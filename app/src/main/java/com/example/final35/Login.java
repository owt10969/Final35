package com.example.final35;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class Login extends AppCompatActivity {
    private CallbackManager callbackManager;
    private LoginButton FBloginButton;

    private GoogleSignInClient googleSignInClient;
    private SignInButton GoogleButton;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        FBlogin();
        GoogleLogin();
        button=findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Login.this,MainView.class);
                startActivity(intent);
            }
        });
    }
    private void FBlogin(){
        FBloginButton=findViewById(R.id.login_button);
        //Create Callback Manager
        callbackManager =CallbackManager.Factory.create();

        //Registerd the CallbackManager with the LoginButton
        FBloginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // Retrieving access token using the LoginResult
                Intent intent =new Intent();
                intent.setClass(Login.this,MainView.class);
                startActivity(intent);
            }
            @Override
            public void onCancel() {}
            @Override
            public void onError(FacebookException error) {}
        });
    }
    public void onActivityResult(int requestCode, int resulrCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resulrCode, data);
        super.onActivityResult(requestCode, resulrCode, data);

        if (resulrCode == Activity.RESULT_OK)
            switch (requestCode) {
                case 101:
                    try {
                        // The Task returned from this call is always completed, no need to attach
                        // a listener.
                        Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
                        GoogleSignInAccount account = task.getResult(ApiException.class);
                        onLoggedIn(account);
                    } catch (ApiException e) {
                        // The ApiException status code indicates the detailed failure reason.
                        Log.w("TAG", "signInResult:failed code=" + e.getStatusCode());
                    }
                    break;
            }
    }

    private boolean isLoggedIn(){
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        return !(accessToken==null||accessToken.getPermissions().isEmpty());
    }
    /**********************************************/
    private void GoogleLogin(){
        GoogleButton=findViewById(R.id.sign_in_button);
        GoogleSignInOptions gso =new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail().build();

        googleSignInClient = GoogleSignIn.getClient(this,gso);
        GoogleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signInIntent = googleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, 101);
            }
        });
    }

    private void onLoggedIn(GoogleSignInAccount googleSignInAccount) {
        Intent intent = new Intent(this,MainView.class);
        intent.putExtra(MainActivity.GOOGLE_ACCOUNT, googleSignInAccount);
        startActivity(intent);
        finish();
    }

    public void onStart() {
        super.onStart();
        if(isLoggedIn()){
            Intent intent =new Intent();
            intent.setClass(Login.this,MainView.class);
            startActivity(intent);
        }

        GoogleSignInAccount alreadyloggedAccount = GoogleSignIn.getLastSignedInAccount(this);
        if (alreadyloggedAccount != null) {
            Intent intent=new Intent();
            intent.setClass(Login.this,MainView.class);
            startActivity(intent);
        } else {
            Log.d("TAG", "Not logged in");
        }
    }
}
