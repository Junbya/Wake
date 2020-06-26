package com.cwj0722.wakeup;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends WakeUpActivity{

    String ad;
    String at;
    String pd;
    String pt;
    String song;
    String dbname = "myDB";
    String tablename = "customer";
    String sql;
    SQLiteDatabase db;
    Cursor resultset;
    String[] result;
    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        db = openOrCreateDatabase(dbname, MODE_PRIVATE, null);
        list = (ListView) findViewById(R.id.LV1);


        try {

            sql = "select * from " + tablename;
            resultset = db.rawQuery(sql, null);

            int count = resultset.getCount();
            result = new String[count];

            for(int i = 0; i < count; i++) {
                resultset.moveToNext();
                String AmDay = ad;
                String PmDay = pd;
                String AmTime = at;
                String PmTime = pt;

                result[i] = AmDay + " " + AmTime + "\n" + PmDay + " " + PmTime;
            }
            System.out.println("select ok");

            ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, result);
            list.setAdapter(adapter);
        } catch (Exception e) {
            System.out.println("select Error :" + e);
        }
    }
}
