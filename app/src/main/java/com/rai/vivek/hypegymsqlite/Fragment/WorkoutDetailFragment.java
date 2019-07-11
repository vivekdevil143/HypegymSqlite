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
import android.widget.TextView;

import com.rai.vivek.hypegymsqlite.R;


public class WorkoutDetailFragment extends Fragment implements View.OnClickListener {

    TextView monday,tuesday,wednesday,thursday,friday,saturday,sunday;
    public WorkoutDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_workout_detail, container, false);

        AppCompatActivity activity = (AppCompatActivity) getActivity();


        Toolbar mToolbar = view.findViewById(R.id.toolbar);
        view.findViewById(R.id.back_arrow).setOnClickListener(this);
        view.findViewById(R.id.cardview_monday).setOnClickListener(this);
        view.findViewById(R.id.cardview_tuesday).setOnClickListener(this);
        view.findViewById(R.id.cardview_wednesday).setOnClickListener(this);
        view.findViewById(R.id.cardview_thursday).setOnClickListener(this);
        view.findViewById(R.id.cardview_friday).setOnClickListener(this);
        view.findViewById(R.id.cardview_saturday).setOnClickListener(this);
        view.findViewById(R.id.cardview_sunday).setOnClickListener(this);

       /* final String Monday =monday.getText().toString().trim();
        final String Tuesday = tuesday.getText().toString().trim();
        final String Wednesday = wednesday.getText().toString().trim();
        final String Thursday = thursday.getText().toString().trim();
        final String Friday = friday.getText().toString().trim();
        final String Saturday = saturday.getText().toString().trim();
        final String Sunday = sunday.getText().toString().trim();
*/
        return view;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.back_arrow:
                getActivity().onBackPressed();
                break;

            /*case R.id.cardview_monday:

               Intent intent=new Intent(getActivity(),timeActivity.class);
               startActivity(intent);
                break;*/
            case R.id.cardview_monday:
                Fragment fragment = new WorkoutTimeFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

                break;

               /*TextView textView = view.findViewById(R.id.monday);
                Intent intent = new Intent(getActivity(),WorkoutTimeFragment.class);
                intent.putExtra("textViewText", textView.getText().toString());
                startActivity(intent);*/


            case R.id.cardview_tuesday:
                Fragment fragment1 = new WorkoutTimeFragment();
                FragmentManager fragmentManager1 = getFragmentManager();
                FragmentTransaction fragmentTransaction1 = fragmentManager1.beginTransaction();
                fragmentTransaction1.replace(R.id.fragment_container, fragment1);
                fragmentTransaction1.addToBackStack(null);
                fragmentTransaction1.commit();
                break;

            case R.id.cardview_wednesday:
                Fragment fragment2 = new WorkoutTimeFragment();
                FragmentManager fragmentManager2 = getFragmentManager();
                FragmentTransaction fragmentTransaction2 = fragmentManager2.beginTransaction();
                fragmentTransaction2.replace(R.id.fragment_container, fragment2);
                fragmentTransaction2.addToBackStack(null);
                fragmentTransaction2.commit();
                break;

            case R.id.cardview_thursday:
                Fragment fragment3 = new WorkoutTimeFragment();
                FragmentManager fragmentManager3 = getFragmentManager();
                FragmentTransaction fragmentTransaction3 = fragmentManager3.beginTransaction();
                fragmentTransaction3.replace(R.id.fragment_container, fragment3);
                fragmentTransaction3.addToBackStack(null);
                fragmentTransaction3.commit();
                break;
            case R.id.cardview_friday:
                Fragment fragment4 = new WorkoutTimeFragment();
                FragmentManager fragmentManager4 = getFragmentManager();
                FragmentTransaction fragmentTransaction4 = fragmentManager4.beginTransaction();
                fragmentTransaction4.replace(R.id.fragment_container, fragment4);
                fragmentTransaction4.addToBackStack(null);
                fragmentTransaction4.commit();
                break;


            case R.id.cardview_saturday:
                Fragment fragment5 = new WorkoutTimeFragment();
                FragmentManager fragmentManager5 = getFragmentManager();
                FragmentTransaction fragmentTransaction5 = fragmentManager5.beginTransaction();
                fragmentTransaction5.replace(R.id.fragment_container, fragment5);
                fragmentTransaction5.addToBackStack(null);
                fragmentTransaction5.commit();
                break;


            case R.id.cardview_sunday:
                Fragment fragment6 = new WorkoutTimeFragment();
                FragmentManager fragmentManager6 = getFragmentManager();
                FragmentTransaction fragmentTransaction6 = fragmentManager6.beginTransaction();
                fragmentTransaction6.replace(R.id.fragment_container, fragment6);
                fragmentTransaction6.addToBackStack(null);
                fragmentTransaction6.commit();
                break;
        }
    }
}