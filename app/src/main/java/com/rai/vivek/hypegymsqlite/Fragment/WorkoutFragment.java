package com.rai.vivek.hypegymsqlite.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.rai.vivek.hypegymsqlite.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class WorkoutFragment extends Fragment implements View.OnClickListener {


    public WorkoutFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_workout, container, false);

        AppCompatActivity activity = (AppCompatActivity) getActivity();

        Toolbar mToolbar = view.findViewById(R.id.toolbar);
        view.findViewById(R.id.back_arrow).setOnClickListener(this);
        view.findViewById(R.id.dayschedular).setOnClickListener(this);
        view.findViewById(R.id.workout).setOnClickListener(this);
        view.findViewById(R.id.dietplanner).setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.back_arrow:
                getActivity().onBackPressed();
                break;
            case R.id.dayschedular:
                Fragment fragment = new DaySchedularFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                break;
            case R.id.workout:
                Fragment fragment1 = new WorkoutDetailFragment();
                FragmentManager fragmentManager1 = getFragmentManager();
                FragmentTransaction fragmentTransaction1 = fragmentManager1.beginTransaction();
                fragmentTransaction1.replace(R.id.fragment_container, fragment1);
                fragmentTransaction1.addToBackStack(null);
                fragmentTransaction1.commit();
                break;
            case R.id.dietplanner:
                Fragment fragment2 = new DietPlanFragment();
                FragmentManager fragmentManager2 = getFragmentManager();
                FragmentTransaction fragmentTransaction2 = fragmentManager2.beginTransaction();
                fragmentTransaction2.replace(R.id.fragment_container, fragment2);
                fragmentTransaction2.addToBackStack(null);
                fragmentTransaction2.commit();
                break;
        }
    }
}
