package com.ishagurung.sunrisegrocery.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.ishagurung.sunrisegrocery.home.MainActivity;
import com.ishagurung.sunrisegrocery.R;
import com.ishagurung.sunrisegrocery.userAccount.UserAccountActivity;
import com.ishagurung.sunrisegrocery.utils.SharedPrefUtils;

public class SplashScreenActivity extends AppCompatActivity {

    boolean isLoggedIn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                if (isLoggedIn)
                    startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
                else
                    startActivity(new Intent(SplashScreenActivity.this, UserAccountActivity.class));
                finish();
            }
        }, 1000);
    }

    public void getIsLoggedInOrNot(){
        isLoggedIn = SharedPrefUtils.getBool(this, "issl", false);
    }
}