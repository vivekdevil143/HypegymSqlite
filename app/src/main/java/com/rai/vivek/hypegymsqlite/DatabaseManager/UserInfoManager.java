package com.rai.vivek.hypegymsqlite.DatabaseManager;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class UserInfoManager extends SQLiteOpenHelper {

    //GymTimeTable
    private static final String DATABASENAME = "UserInfo.db";
    private static final int DATABASEVERSION = 1;
    private String TABLENAME = "UserInformation";
    public static final String COLUMNID = "id";
    public static final String COLUMN_INTIME = "intime";
    public static final String COLUMN_OUTTIME = "outtime";
    public static final String COLUMN_DESCRIPTION = "description";


    public UserInfoManager(Context context) {
        super(context, DATABASENAME, null, DATABASEVERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE " + TABLENAME + " (\n" +
                "    " + COLUMNID + " INTEGER NOT NULL CONSTRAINT employees_pk PRIMARY KEY AUTOINCREMENT,\n" +
                "    " + COLUMN_INTIME + " varchar(200) NOT NULL,\n" +
                "    " + COLUMN_OUTTIME + " varchar(200) NOT NULL ,\n" +
                "    " + COLUMN_DESCRIPTION + " varchar(200) NOT NULL\n" +
                ");";

        /*
         * Executing the string to create the table
         * */
        sqLiteDatabase.execSQL(sql);
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        String sql = "DROP TABLE IF EXISTS " + TABLENAME + ";";
        sqLiteDatabase.execSQL(sql);
        onCreate(sqLiteDatabase);
    }


    public Boolean registerUserInfo(String intime, String outtime, String description) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_INTIME, intime);
        contentValues.put(COLUMN_OUTTIME, outtime);
        contentValues.put(COLUMN_DESCRIPTION, description);
        return db.insert(TABLENAME, null, contentValues) != 1;
    }

    public Cursor getUserInformation() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM " + TABLENAME, null);
    }
}
