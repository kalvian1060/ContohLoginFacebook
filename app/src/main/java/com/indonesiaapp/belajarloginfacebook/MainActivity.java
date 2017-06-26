package com.indonesiaapp.belajarloginfacebook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

public class MainActivity extends AppCompatActivity {
    private TextView tvInfo;
    private LoginButton btnLogin;
    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager=CallbackManager.Factory.create();
        setContentView(R.layout.activity_main);
        tvInfo=(TextView)findViewById(R.id.tvInfo);
        btnLogin=(LoginButton)findViewById(R.id.btnLogin);
        btnLogin.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                tvInfo.setText("User ID : "+loginResult.getAccessToken().getUserId()+"\n"+" Auth Token"+loginResult.getAccessToken().getToken());
            }

            @Override
            public void onCancel() {
                tvInfo.setText("Login batal");
            }

            @Override
            public void onError(FacebookException error) {
                tvInfo.setText("Login bermasalah..");
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode,resultCode,data);
    }
}
