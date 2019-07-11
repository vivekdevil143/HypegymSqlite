package com.rai.vivek.hypegymsqlite.Activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.rai.vivek.hypegymsqlite.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent loginScreen=new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(loginScreen);
                finish();
            }
        },3000);//sleep time
    }
}
