package com.frandog.a20180117_01.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Student on 2018/1/22.
 */
//教完20180122_02SQLite資料庫後，將此專案改成寫入SQL裡，新增此class
public class MyDBHelper extends SQLiteOpenHelper {
    final static String DB_NAME = "student.sqlite";
    final static int VERSION = 1;
    public MyDBHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE \"students\" ( `_id` INTEGER, `name` TEXT, `score` INTEGER, PRIMARY KEY(`_id`) )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
