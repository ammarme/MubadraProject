package com.ammar.mubadraproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class IntroWelcomActivity extends AppCompatActivity {
private  static  int SPLASH_SCREEN_TIME_OUT= 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_welcom_);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent  = new Intent(IntroWelcomActivity.this,MainActivity.class);
                startActivity(intent);
                finish();

                }


        },SPLASH_SCREEN_TIME_OUT);
    }
}