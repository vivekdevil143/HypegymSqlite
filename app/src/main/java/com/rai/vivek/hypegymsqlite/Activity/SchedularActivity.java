package com.rai.vivek.hypegymsqlite.Activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.rai.vivek.hypegymsqlite.Fragment.WorkoutFragment;
import com.rai.vivek.hypegymsqlite.R;
import com.rai.vivek.hypegymsqlite.SharedPref;

public class SchedularActivity extends AppCompatActivity implements View.OnClickListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedular);

        Toolbar mToolbar = findViewById(R.id.toolbar);

        findViewById(R.id.schedular).setOnClickListener(this);
        findViewById(R.id.logoutBtn).setOnClickListener(this);
        findViewById(R.id.back_arrow).setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (!SharedPref.getInstance(this).isLoggedIn()) {
            Intent intent = new Intent(this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.schedular:


                Fragment fragment = new WorkoutFragment();
                FragmentManager fragmentManager =getSupportFragmentManager() ;
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentManager.popBackStack();
                fragmentTransaction.commit();

                break;
                
            case R.id.logoutBtn:
                
                Logout();
                break;

            case R.id.back_arrow:
                onBackPressed();
                break;
        }
    }

    private void Logout() {
        SharedPref.getInstance(this).clear();
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
