package com.jk.sqlite_android.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    public static final String dbName = "temp";
    public static final int dbVersion = 1;
    public static final String col_id = "_id";
    public static final String col_name = "name";
    public static final String col_password = "password";
    public static final String tableName = "emp";


    public MyDatabaseHelper(Context context) {
        super(context, dbName, null, dbVersion);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        try {
            String sql = "CREATE TABLE " + tableName + " ( " + col_id + " INTEGER PRIMARY KEY AUTOINCREMENT ," + col_name + " TEXT ," + col_password + " TEXT )";
            db.execSQL(sql);
            Log.d("kartik", "onCreate");

        } catch (Exception e) {
            Log.d("kartik", e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("jk", "onUpgrade");
    }
}
