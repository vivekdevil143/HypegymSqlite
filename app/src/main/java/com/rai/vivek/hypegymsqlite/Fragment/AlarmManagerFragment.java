package com.rai.vivek.hypegymsqlite.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rai.vivek.hypegymsqlite.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AlarmManagerFragment extends Fragment {


    public AlarmManagerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_alarm_manager, container, false);
    }

}
