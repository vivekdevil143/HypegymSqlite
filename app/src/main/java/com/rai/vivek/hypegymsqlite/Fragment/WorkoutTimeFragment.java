package com.rai.vivek.hypegymsqlite.Fragment;


import android.annotation.SuppressLint;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;


import com.rai.vivek.hypegymsqlite.DatabaseManager.UserInfoManager;
import com.rai.vivek.hypegymsqlite.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class WorkoutTimeFragment extends Fragment implements View.OnClickListener {


    UserInfoManager db;

    private TextView IntimeTxt, OuttimeTxt;
    private EditText editTextdesc;

    public WorkoutTimeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_workout_time, container, false);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        final CardView cardView1 = view.findViewById(R.id.cardview1);
        final CardView cardView2 = view.findViewById(R.id.cardview2);
        IntimeTxt = view.findViewById(R.id.intime);
        OuttimeTxt = view.findViewById(R.id.outtime);
        editTextdesc = view.findViewById(R.id.edittextdescription);

        db = new UserInfoManager(getContext());


        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @SuppressLint({"ResourceAsColor", "SimpleDateFormat"})
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        String final_time = null;
                        SimpleDateFormat sdf = new SimpleDateFormat("H:MM");
                        try {
                            Date date = sdf.parse(selectedHour + ":" + selectedMinute);
                            final_time = new SimpleDateFormat("K:MM a").format(date);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        IntimeTxt.setText(final_time);
                    }
                }, hour, minute, false);//Yes 24 hour time
                mTimePicker.setTitle("Select Open Time");
                mTimePicker.setCancelable(false);
                mTimePicker.show();


            }
        });

        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Calendar mcurrentTimec = Calendar.getInstance();
                int hourc = mcurrentTimec.get(Calendar.HOUR_OF_DAY);
                int minutec = mcurrentTimec.get(Calendar.MINUTE);
                TimePickerDialog mTimePickerc;
                mTimePickerc = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @SuppressLint("SimpleDateFormat")
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        String final_timec = null;
                        SimpleDateFormat sdf = new SimpleDateFormat("H:MM");
                        try {
                            Date date = sdf.parse(selectedHour + ":" + selectedMinute);
                            final_timec = new SimpleDateFormat("K:MM a").format(date);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        OuttimeTxt.setText(final_timec);
                    }
                }, hourc, minutec, false);//Yes 24 hour time
                mTimePickerc.setTitle("Select Closing Time");
                mTimePickerc.setCancelable(false);
                mTimePickerc.show();

            }
        });

        Toolbar mToolbar = view.findViewById(R.id.toolbar);
        view.findViewById(R.id.back_arrow).setOnClickListener(this);
        view.findViewById(R.id.viewuser).setOnClickListener(this);
        view.findViewById(R.id.submitbtn).setOnClickListener(this);

        return view;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_arrow:
                getActivity().onBackPressed();
                break;

            case R.id.viewuser:

                Fragment fragment = new ViewUserDetailsFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                break;

            case R.id.submitbtn:
                registerUserInfo();
                break;
        }
    }


    private void registerUserInfo() {
        String intime = IntimeTxt.getText().toString().trim();
        String outtime = OuttimeTxt.getText().toString().trim();
        String description = editTextdesc.getText().toString().trim();

        if (intime.isEmpty()) {
            IntimeTxt.setError("InTime is required");
            IntimeTxt.requestFocus();
            return;
        }
        if (outtime.isEmpty()) {
            OuttimeTxt.setError("OutTime is required");
            OuttimeTxt.requestFocus();
            return;
        }
        if (description.isEmpty()) {
            editTextdesc.setError("Description is required");
            editTextdesc.requestFocus();
            return;
        }


        Boolean insert = db.registerUserInfo(intime, outtime, description);
        if (insert) {
            Toast.makeText(getContext(), "User Information save Successfully", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(getContext(), "Failed to save user Information!Try Again", Toast.LENGTH_SHORT).show();
        }
    }


}
