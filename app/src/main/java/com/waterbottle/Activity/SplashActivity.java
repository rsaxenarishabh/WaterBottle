package com.waterbottle.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.waterbottle.Activity.login.LoginActivity;
import com.waterbottle.DashboardActivity;
import com.waterbottle.R;
import com.waterbottle.utils.AmplefreshPrefs;
import com.waterbottle.utils.Constants;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (AmplefreshPrefs.getBoolean(getApplicationContext(), Constants.LOGIN_CHECK)) {
                    Intent intent = new Intent(SplashActivity.this, DashboardActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
        }, 3000);


    }
}