package com.rai.vivek.hypegymsqlite.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import com.rai.vivek.hypegymsqlite.DatabaseManager.UserInfoManager;
import com.rai.vivek.hypegymsqlite.ModelClass.UserInfo;
import com.rai.vivek.hypegymsqlite.R;


import java.util.List;

public class UserInfoAdapter extends ArrayAdapter<UserInfo> {
    Context mCtx;
    List<UserInfo> UserInfoList;
    int layoutRes1;
    UserInfoManager db;

    public UserInfoAdapter(Context mCtx, List<UserInfo> UserInfoList, int layoutRes, UserInfoManager db) {
        super(mCtx, layoutRes, UserInfoList);
        this.mCtx = mCtx;
        this.UserInfoList = UserInfoList;
        this.layoutRes1 = layoutRes;
        this.db = db;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(layoutRes1, null);
        TextView textViewIntime = view.findViewById(R.id.textViewIntime);
        TextView textViewOuttime = view.findViewById(R.id.textViewOutime);
        TextView textViewDesc = view.findViewById(R.id.textViewDesc);


        final UserInfo user = UserInfoList.get(position);


        textViewIntime.setText(user.getIntime());
        textViewOuttime.setText(user.getOuttime());
        textViewDesc.setText(user.getDesc());


        return view;

    }
}
