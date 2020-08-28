package com.jk.sqlite_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.jk.sqlite_android.database.MyDatabaseHelper;

import java.util.ArrayList;

public class ShowSqliteData extends AppCompatActivity {

    private ArrayList<String> list_id = new ArrayList<String>();
    private ArrayList<String> list_name = new ArrayList<String>();
    private ArrayList<String> list_pass = new ArrayList<String>();
    ListAdaptor adaptor;

    MyDatabaseHelper helper;
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_sqlite_data);

        helper = new MyDatabaseHelper(this);
        getDataFromSQL();
        lv = (ListView) findViewById(R.id.lv);

        adaptor = new ListAdaptor(this, list_id, list_name, list_pass);
        lv.setAdapter(adaptor);
    }

    void getDataFromSQL() {
        SQLiteDatabase database = helper.getReadableDatabase();

        Cursor cursor = database.rawQuery("SELECT * FROM " + MyDatabaseHelper.tableName, new String[]{});
        if (cursor != null) {
            cursor.moveToFirst();
        }
        do {
            list_id.add(cursor.getString(0));
            list_name.add(cursor.getString(1));
            list_pass.add(cursor.getString(2));
        } while (cursor.moveToNext());
    }

    private class ListAdaptor extends BaseAdapter {

        private ViewHolder holder;

        private Context context;
        private ArrayList<String> list_id = new ArrayList<String>();
        private ArrayList<String> list_name = new ArrayList<String>();
        private ArrayList<String> list_pass = new ArrayList<String>();

        public ListAdaptor(Context context, ArrayList<String> list_id, ArrayList<String> list_name, ArrayList<String> list_pass) {
            this.context = context;
            this.list_id = list_id;
            this.list_name = list_name;
            this.list_pass = list_pass;
        }

        @Override
        public int getCount() {
            return list_id.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.card, parent, false);
                holder = new ViewHolder();
                holder.show_name = (TextView) convertView.findViewById(R.id.show_name);
                holder.show_password = (TextView) convertView.findViewById(R.id.show_password);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.show_name.setText(list_name.get(position));
            holder.show_password.setText(list_pass.get(position));
            return convertView;
        }

        private class ViewHolder {
            TextView show_password;
            TextView show_name;
        }
    }
}