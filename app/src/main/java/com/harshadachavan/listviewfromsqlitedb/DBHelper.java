package com.harshadachavan.listviewfromsqlitedb;

/**
 * Created by Harshada Chavan on 10/3/2017.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public SQLiteDatabase DB;
    public String DBPath;
    public static String DBName = "sample";
    public static final int version = '1';
    public static Context currentContext;
    public static String tableName = "Resource";

    public DBHelper(Context context) {
        super(context, DBName, null, version);
        currentContext = context;
        DBPath = "/data/data/" + context.getPackageName() + "/databases";
        createDatabase();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
// TODO Auto-generated method stub

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
// TODO Auto-generated method stub

    }

    private void createDatabase() {
        boolean dbExists = checkDbExists();

        if (dbExists) {
// do nothing
        } else {
            DB = currentContext.openOrCreateDatabase(DBName, 0, null);
            DB.execSQL("CREATE TABLE IF NOT EXISTS " +
                    tableName +
                    " (LastName TEXT, FirstName TEXT, id INTEGER PRIMARY KEY AUTOINCREMENT);");

            DB.execSQL("INSERT INTO " +
                    tableName +
                    " Values ('Chavan','Harshada',1);");
            DB.execSQL("INSERT INTO " +
                    tableName +
                    " Values ('Chavan','Rushikesh',2);");
            DB.execSQL("INSERT INTO " +
                    tableName +
                    " Values ('Tendulkar','Sachin',3);");
            DB.execSQL("INSERT INTO " +
                    tableName +
                    " Values ('Dhoni','Mahendra',4);");
            DB.execSQL("INSERT INTO " +
                    tableName +
                    " Values ('Kohli','Virat', 5);");
            DB.execSQL("INSERT INTO " +
                    tableName +
                    " Values ('Dravid','Rahul',6);");
        }

    }

    private boolean checkDbExists() {
        SQLiteDatabase checkDB = null;

        try {
            String myPath = DBPath + DBName;
            checkDB = SQLiteDatabase.openDatabase(myPath, null,
                    SQLiteDatabase.OPEN_READONLY);

        } catch (SQLiteException e) {

// database does't exist yet.

        }

        if (checkDB != null) {

            checkDB.close();

        }

        return checkDB != null ? true : false;
    }
}
