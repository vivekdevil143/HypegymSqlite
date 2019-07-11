package com.rai.vivek.hypegymsqlite.Activity;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.rai.vivek.hypegymsqlite.Adapter.RegistrationAdapter;
import com.rai.vivek.hypegymsqlite.DatabaseManager.DatabaseManager;
import com.rai.vivek.hypegymsqlite.ModelClass.Registration;
import com.rai.vivek.hypegymsqlite.R;

import java.util.ArrayList;
import java.util.List;

public class ViewRegisteredActivity extends AppCompatActivity {
    DatabaseManager mDatabase;

    List<Registration> RegistrationList;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_registered);

        RegistrationList = new ArrayList<>();
        listView = findViewById(R.id.listviewRegistered);
        mDatabase = new DatabaseManager(this);

        loadEmployeesFromDatabase();
    }

    private void loadEmployeesFromDatabase() {

        Cursor cursor = mDatabase.getAllEmployees();
        if (cursor.moveToFirst()) {
            do {
                RegistrationList.add(new Registration(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5)
                        /*cursor.getString(6),
                        cursor.getString(7)*/

                ));
            } while (cursor.moveToNext());

            RegistrationAdapter adapter = new RegistrationAdapter(this, RegistrationList, R.layout.list_registered_users, mDatabase);
            listView.setAdapter(adapter);
        }

    }
}
