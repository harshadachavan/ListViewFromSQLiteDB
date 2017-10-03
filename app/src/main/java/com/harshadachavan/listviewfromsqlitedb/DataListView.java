package com.harshadachavan.listviewfromsqlitedb;

/**
 * Created by Harshada Chavan on 10/3/2017.
 */

import java.util.ArrayList;

import android.app.ListActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class DataListView extends ListActivity {

    private ArrayList<String> results = new ArrayList<String>();
    private String tableName = DBHelper.tableName;
    private SQLiteDatabase newDB;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        openAndQueryDatabase();

        displayResultList();

    }
    private void displayResultList() {
        TextView tView = new TextView(this);
        tView.setText("This data is retrieved from the database ");
        getListView().addHeaderView(tView);

        setListAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, results));
        getListView().setTextFilterEnabled(true);

    }
    private void openAndQueryDatabase() {
        try {
            DBHelper dbHelper = new DBHelper(this.getApplicationContext());
            newDB = dbHelper.getWritableDatabase();
            Cursor c = newDB.rawQuery("SELECT id, FirstName, LastName FROM " +
                    tableName , null);

            if (c != null ) {
                if (c.moveToFirst()) {
                    do {
                        int id = c.getInt(c.getColumnIndex("id"));
                        String firstName = c.getString(c.getColumnIndex("FirstName"));
                        String lastName = c.getString(c.getColumnIndex("LastName"));

                        results.add("Id: "+id+", Name: " + firstName + ", Lastname: " + lastName);
                    }while (c.moveToNext());
                }
            }
        } catch (SQLiteException se ) {
            Log.e(getClass().getSimpleName(), "Could not create or Open the database");
        } finally {
            if (newDB != null)
                newDB.execSQL("DELETE FROM " + tableName);
            newDB.close();
        }

    }
}
