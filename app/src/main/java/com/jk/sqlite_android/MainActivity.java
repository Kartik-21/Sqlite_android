package com.jk.sqlite_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jk.sqlite_android.database.MyDatabaseHelper;

public class MainActivity extends AppCompatActivity {

    MyDatabaseHelper helper;
    private TextView v;
    private Button next, btn;
    private EditText name, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        helper = new MyDatabaseHelper(this);
        //   SQLiteDatabase database = helper.getReadableDatabase();

        v = (TextView) findViewById(R.id.v);
        name = (EditText) findViewById(R.id.name);
        pass = (EditText) findViewById(R.id.pass);
        btn = (Button) findViewById(R.id.btn);
        next = (Button) findViewById(R.id.next);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String n = name.getText().toString();
                String p = pass.getText().toString();

                Long s = insertData(n, p);
                //   Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
                if (s < 0) {
                    Toast.makeText(MainActivity.this, "UnSuccessful", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "Successful", Toast.LENGTH_LONG).show();
                }
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String s = getData();
//                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(MainActivity.this, ShowSqliteData.class);
                startActivity(intent);
            }
        });

    }


    //insert data into sqlite
    public long insertData(String name, String pass) {
        SQLiteDatabase database = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MyDatabaseHelper.col_name, name);
        values.put(MyDatabaseHelper.col_password, pass);
        long id = database.insert(MyDatabaseHelper.tableName, null, values);
        return id;
    }


    //get data from sqlite
    public String getData() {
        SQLiteDatabase database = helper.getReadableDatabase();
        StringBuilder builder = new StringBuilder();

        Cursor cursor = database.rawQuery("SELECT " + MyDatabaseHelper.col_name + " , " + MyDatabaseHelper.col_password + " FROM " + MyDatabaseHelper.tableName, new String[]{});
        if (cursor != null) {
            cursor.moveToFirst();
        }
        do {
            String name = cursor.getString(0);
            String pass = cursor.getString(1);
            builder.append(name + " " + pass + "   ");

        } while (cursor.moveToNext());
        return builder.toString();
    }

}