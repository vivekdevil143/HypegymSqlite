package com.rai.vivek.hypegymsqlite.Fragment;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.rai.vivek.hypegymsqlite.Adapter.UserInfoAdapter;
import com.rai.vivek.hypegymsqlite.DatabaseManager.UserInfoManager;
import com.rai.vivek.hypegymsqlite.ModelClass.UserInfo;
import com.rai.vivek.hypegymsqlite.R;

import java.util.ArrayList;
import java.util.List;


public class ViewUserDetailsFragment extends Fragment {
    UserInfoManager db;
    List<UserInfo> userInfoList;
    ListView list_view;


    public ViewUserDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view_user_details, container, false);

        userInfoList = new ArrayList<>();
        list_view = view.findViewById(R.id.userInfolistview);
        db = new UserInfoManager(getContext());

        loadUserInfofromDatabase();

        return view;
    }

    private void loadUserInfofromDatabase() {


        Cursor cursor = db.getUserInformation();
        if (cursor.moveToFirst()) {
            do {
                userInfoList.add(new UserInfo(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3)

                ));
            } while (cursor.moveToNext());

            UserInfoAdapter adapter = new UserInfoAdapter(getContext(), userInfoList, R.layout.fragment_view_user_details, db);
            list_view.setAdapter(adapter);
        }


    }
}