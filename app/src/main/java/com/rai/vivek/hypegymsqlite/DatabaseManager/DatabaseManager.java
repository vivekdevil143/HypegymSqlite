package com.rai.vivek.hypegymsqlite.DatabaseManager;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.rai.vivek.hypegymsqlite.ModelClass.Registration;

public class DatabaseManager extends  SQLiteOpenHelper {


    public static final String DATABASE_NAME = "UserRegistraion";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "Registraion";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_ADDRESS = "Address";
    /*  private static final String COLUMN_DOB = "Dateofbirth";
      private static final String COLUMN_DOJ = "Dateofjoining";*/






    public DatabaseManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE " + TABLE_NAME + " (\n" +
                "    " + COLUMN_ID + " INTEGER NOT NULL CONSTRAINT employees_pk PRIMARY KEY AUTOINCREMENT,\n" +
                "    " + COLUMN_NAME + " varchar(200) NOT NULL,\n" +
                "    " + COLUMN_EMAIL + " varchar(200) NOT NULL ,\n" +
                "    " + COLUMN_PHONE + " INTEGER NOT NULL,\n" +
                "    " + COLUMN_PASSWORD + " varchar(200) NOT NULL,\n" +
                "    " + COLUMN_ADDRESS + " varchar(200) NOT NULL\n" +
            /*    "    " + COLUMN_DOB + " date NOT NULL,\n" +
                "    " + COLUMN_DOJ + " date NOT NULL\n" +*/
                ");";

        /*
         * Executing the string to create the table
         * */
        sqLiteDatabase.execSQL(sql);
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        String sql = "DROP TABLE IF EXISTS " + TABLE_NAME + ";";
        sqLiteDatabase.execSQL(sql);
        onCreate(sqLiteDatabase);
    }

    public boolean registerUser(String name, String email, String phone, String password, String address/*, String dob,  String doj*/) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_EMAIL, email);
        cv.put(COLUMN_PHONE, phone);
        cv.put(COLUMN_PASSWORD, password);
        cv.put(COLUMN_ADDRESS, address);
        /*cv.put(COLUMN_DOB, dob);
        cv.put(COLUMN_DOJ, doj);*/


        return sqLiteDatabase.insert(TABLE_NAME, null, cv) != 1;

    }

    public Cursor getAllEmployees() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }

 /*   boolean UpdateEmployee(int id ,String name, String dept , double salary){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_DEPT, dept);
        cv.put(COLUMN_SALARY, String.valueOf(salary));

        return sqLiteDatabase.update(TABLE_NAME,cv,COLUMN_ID+"=?",new String[]{String.valueOf(id)})>0;
    }

    boolean DeleteEmployee(int id){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.delete(TABLE_NAME,COLUMN_ID+"=?",new String[]{String.valueOf(id)})>0;
    }
*/


    public Registration Authenticate(Registration user) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME,// Selecting Table
                new String[]{COLUMN_ID, COLUMN_NAME, COLUMN_EMAIL, COLUMN_PHONE, COLUMN_PASSWORD, COLUMN_ADDRESS},//Selecting columns want to query
                COLUMN_EMAIL + "=?",
                new String[]{user.email},//Where clause
                null, null, null);

        if (cursor != null && cursor.moveToFirst() && cursor.getCount() > 0) {
            //if cursor has value then in user database there is user associated with this given email
            Registration user1 = new Registration(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5));

            //Match both passwords check they are same or not
            if (user.password.equalsIgnoreCase(user1.password)) {
                return user1;
            }
        }

        //if user password does not matches or there is no record with that email then return @false
        return null;
    }

    public boolean isEmailExists(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME,// Selecting Table
                new String[]{COLUMN_ID, COLUMN_NAME, COLUMN_EMAIL, COLUMN_PHONE, COLUMN_PASSWORD, COLUMN_ADDRESS},//Selecting columns want to query
                COLUMN_EMAIL + "=?",
                new String[]{email},//Where clause
                null, null, null);

        if (cursor != null && cursor.moveToFirst() && cursor.getCount() > 0) {
            //if cursor has value then in user database there is user associated with this given email so return true
            return true;
        }

        //if email does not exist return false
        return false;
    }




}