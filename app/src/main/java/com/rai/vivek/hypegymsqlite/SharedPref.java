package com.rai.vivek.hypegymsqlite;

import android.content.Context;
import android.content.SharedPreferences;

import com.rai.vivek.hypegymsqlite.ModelClass.Registration;

public class SharedPref {

    private static final String SHARED_PREF_NAME = "my_shared_preff";

    private static SharedPref mInstance;
    private Context mCtx;

    private SharedPref(Context mCtx) {
        this.mCtx = mCtx;
    }


    public static synchronized SharedPref getInstance(Context mCtx) {
        if (mInstance == null) {
            mInstance = new SharedPref(mCtx);
        }
        return mInstance;
    }



    public void saveUser(Registration user) {

        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt("id", user.getId());
        editor.putString("name", user.getName());
        editor.putString("email", user.getEmail());
        editor.putString("phone", user.getPhone());
        editor.putString("address", user.getEmail());

        editor.apply();

    }

    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getInt("id", -1) != -1;
    }

    public Registration getUser() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new Registration(
                sharedPreferences.getInt("id", -1),
                sharedPreferences.getString("name", null),
                sharedPreferences.getString("email", null),
                sharedPreferences.getString("phone", null),
                sharedPreferences.getString("password", null),
                sharedPreferences.getString("address", null)
        );
    }

    public void clear() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
