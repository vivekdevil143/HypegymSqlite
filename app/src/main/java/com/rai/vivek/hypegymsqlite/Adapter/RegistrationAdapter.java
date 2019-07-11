package com.rai.vivek.hypegymsqlite.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.rai.vivek.hypegymsqlite.DatabaseManager.DatabaseManager;
import com.rai.vivek.hypegymsqlite.R;
import com.rai.vivek.hypegymsqlite.ModelClass.Registration;

import java.util.List;

public class RegistrationAdapter extends ArrayAdapter<Registration> {

    Context mCtx;
    List<Registration> registrationList;
    int layoutRes;
    DatabaseManager mDatabase;

    public RegistrationAdapter(Context mCtx, List<Registration> registrationList, int layoutRes, DatabaseManager mDatabase) {
        super(mCtx, layoutRes, registrationList);
        this.mCtx = mCtx;
        this.registrationList = registrationList;
        this.layoutRes = layoutRes;
        this.mDatabase = mDatabase;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(layoutRes, null);
        TextView textViewName = view.findViewById(R.id.textViewName);
        TextView textViewEmail = view.findViewById(R.id.textViewemail);
        TextView textViewphone = view.findViewById(R.id.textViewphone);
        TextView textViewpassword = view.findViewById(R.id.textViewpassword);
        TextView textViewaddress = view.findViewById(R.id.textViewaddress);
       /* TextView textViewdob = view.findViewById(R.id.textViewdob);
        TextView textViewdoj = view.findViewById(R.id.textViewdoj);*/

        final Registration employee = registrationList.get(position);

        textViewName.setText(employee.getName());
        textViewEmail.setText(employee.getEmail());
        textViewphone.setText(employee.getPhone());
        textViewpassword.setText(employee.getPassword());
        textViewaddress.setText(employee.getAddress());
       /* textViewdob.setText(employee.getDob());
        textViewdoj.setText(employee.getDoj());*/


        return view;

    }






}