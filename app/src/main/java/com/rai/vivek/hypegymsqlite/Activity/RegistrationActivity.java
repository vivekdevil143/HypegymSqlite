package com.rai.vivek.hypegymsqlite.Activity;

import android.content.Context;
import android.content.Intent;
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
import com.rai.vivek.hypegymsqlite.SharedPref;

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener {

    private RelativeLayout Reg_relativeLayout;
    public static final String DATABASE_NAME = "mydatabse";

    DatabaseManager mDatabase;

    EditText editTextname, editTextemail, editTextphonenumber, editTextpassword, editTextaddress;
   /* TextView textviewdob,textviewdoj;
    Calendar mcurrentDate;
    int day, month, year;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        editTextname = findViewById(R.id.username);
        editTextemail = findViewById(R.id.email);
        editTextphonenumber = findViewById(R.id.phone_number);
        editTextpassword = findViewById(R.id.reg_password);
        editTextaddress = findViewById(R.id.address);
       /* textviewdob = findViewById(R.id.dateofbirth);
        textviewdoj = findViewById(R.id.dateofjoining);
*/
        findViewById(R.id.SignIn_button).setOnClickListener(this);
        findViewById(R.id.Registration).setOnClickListener(this);
        findViewById(R.id.registratedUser).setOnClickListener(this);
        mDatabase = new DatabaseManager(this);


        Reg_relativeLayout = findViewById(R.id.reg_relative_layout);

        Reg_relativeLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                hideKeyboard(v);
                return false;
            }
        });

     /*   mcurrentDate = Calendar.getInstance();
        day = mcurrentDate.get(Calendar.DAY_OF_MONTH);
        month = mcurrentDate.get(Calendar.MONTH);
        year = mcurrentDate.get(Calendar.YEAR);

        month = month + 1;

        //textviewdob.setText(day + "/" + month + "/" + year);
        textviewdob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(RegistrationActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthofYear, int dayOfMonth) {
                        monthofYear = monthofYear + 1;
                        textviewdob.setText(dayOfMonth + "/" + monthofYear + "/" + year);

                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        //textviewdoj.setText(day + "/" + month + "/" + year);
        textviewdoj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(RegistrationActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthofYear, int dayOfMonth) {
                        monthofYear = monthofYear + 1;
                        textviewdoj.setText(dayOfMonth + "/" + monthofYear + "/" + year);

                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });*/
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
            case R.id.SignIn_button:
                finish();
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                break;
            case R.id.Registration:
                registerUser();
                break;
            case R.id.registratedUser:
                startActivity(new Intent(this, ViewRegisteredActivity.class));
                break;
        }
    }

    private void registerUser() {
        final String name = editTextname.getText().toString().trim();
        final String email = editTextemail.getText().toString().trim();
        final String phone = editTextphonenumber.getText().toString().trim();
        final String password = editTextpassword.getText().toString().trim();
        final String address = editTextaddress.getText().toString().trim();
   /*     final String Dob = textviewdob.getText().toString().trim();
        final String Doj = textviewdoj.getText().toString().trim();*/


        if (name.isEmpty()) {
            editTextname.setError("Name is required");
            editTextname.requestFocus();
            return;
        }

        if (email.isEmpty()) {
            editTextemail.setError("Email is required");
            editTextemail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextemail.setError("Email already existed");
            editTextemail.requestFocus();
            return;
        }
        if (phone.isEmpty()) {
            editTextphonenumber.setError("PhoneNumber is required");
            editTextphonenumber.requestFocus();
            return;
        }

        if (phone.length() < 10) {
            editTextphonenumber.setError("Invalid Phone Number");
            editTextphonenumber.requestFocus();
            return;
        }


        if (password.isEmpty()) {
            editTextpassword.setError("Password is required");
            editTextpassword.requestFocus();
            return;
        }

        if (address.isEmpty()) {
            editTextaddress.setError("Address is required");
            editTextaddress.requestFocus();
            return;
        }

        /*if (Dob.isEmpty()) {
            textviewdob.setError("Date of Birth is required");
            textviewdob.requestFocus();
            return;
        }



        if (Doj.isEmpty()) {
            textviewdoj.setError("Date of is joining required");
            textviewdoj.requestFocus();
            return;
        }

       // Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd ");
        String dob = sdf.format(new Date());

        //Calendar call = Calendar.getInstance();
        SimpleDateFormat sdfa = new SimpleDateFormat("yyyy-mm-dd ");
        String doj = sdfa.format(new Date());*/

        //adding the employee with the DatabaseManager instance
        if (!mDatabase.isEmailExists(email)) {
            mDatabase.registerUser(name, email, phone, password, address);
            Toast.makeText(this, "User Registered Successfully", Toast.LENGTH_SHORT).show();
        } else
            Toast.makeText(this, " Registration Failed!Email exists already", Toast.LENGTH_SHORT).show();
    }


}
