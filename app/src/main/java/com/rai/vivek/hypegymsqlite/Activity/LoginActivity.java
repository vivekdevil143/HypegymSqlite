package com.rai.vivek.hypegymsqlite.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.rai.vivek.hypegymsqlite.DatabaseManager.DatabaseManager;
import com.rai.vivek.hypegymsqlite.R;
import com.rai.vivek.hypegymsqlite.ModelClass.Registration;
import com.rai.vivek.hypegymsqlite.SharedPref;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    RelativeLayout relativeLayout;
    EditText editTextemail, editTextPassword;
    DatabaseManager mDatabase;
    SharedPreferences sharedPref;

    public static final String EMAIL = "email";
    public static final String mypref = "mypref";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextemail = findViewById(R.id.email);
        editTextPassword = findViewById(R.id.Password);
        findViewById(R.id.Sign_up).setOnClickListener(this);
        findViewById(R.id.Login_Button).setOnClickListener(this);

        mDatabase = new DatabaseManager(this);

        relativeLayout = findViewById(R.id.relaive_layout);
        relativeLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                hideKeyboard(v);
                return false;
            }
        });


    }

    protected void hideKeyboard(View view) {
        InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        in.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (SharedPref.getInstance(this).isLoggedIn()) {
            Intent intent = new Intent(this, SchedularActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.Sign_up:
                finish();
                startActivity(new Intent(this, RegistrationActivity.class));


                break;

            case R.id.Login_Button:

                // finish();
                //startActivity(new Intent(this, SchedularActivity.class));
                userLogin();
                break;
        }
    }


    private void userLogin() {

        String email = editTextemail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (email.isEmpty()) {
            editTextemail.setError("Email addressis required");
            editTextemail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextemail.setError("Please Enter Valid Email");
            editTextemail.requestFocus();
            return;
        }


        if (password.isEmpty()) {
            editTextPassword.setError("Password is required");
            editTextPassword.requestFocus();
            return;
        }

        //Authenticate user
        Registration currentUser = mDatabase.Authenticate(new Registration(0, null, email, null, password, null));


        //Check Authentication is successful or not
        if (currentUser != null) {
            SharedPref.getInstance(LoginActivity.this)
                    .saveUser(currentUser);
            Toast.makeText(this, "Login Successfully", Toast.LENGTH_SHORT).show();
            //User Logged in Successfully Launch You home screen activity
            Intent intent = new Intent(this, SchedularActivity.class);
            startActivity(intent);
            finish();
        } else {

            //User Logged in Failed
            Toast.makeText(this, "Failed to log in , please try again", Toast.LENGTH_SHORT).show();

        }

      /*  Boolean chkemailpass = mDatabase.emailpassword(email, password);
        if (chkemailpass) {
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString(EMAIL, email);
            editor.apply();

            Intent intent = new Intent(this, SchedularActivity.class);
            intent.putExtra("email", email);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(getApplicationContext(), "Wrong email or password", Toast.LENGTH_SHORT).show();
        }*/


    }

}


