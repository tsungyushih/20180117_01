package com.frandog.a20180117_01.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by Student on 2018/1/22.
 */
//教完20180122_02SQLite資料庫後，將此專案改成寫入SQL裡，新增此class
public class StudentDAOBImpl implements StudentDAO {
    Context context;
    SQLiteDatabase db;
    public StudentDAOBImpl(Context context)
    {
        this.context = context;
        MyDBHelper helper = new MyDBHelper(context);
        db = helper.getWritableDatabase();
    }

    @Override
    public boolean add(Student s) {
        ContentValues cv = new ContentValues();
        cv.put("_id", s.id);
        cv.put("name", s.name);
        cv.put("score", s.score);
        db.insert("students", null, cv);
        return true;
    }

    @Override
    public ArrayList<Student> getList() {
        ArrayList<Student> mylist = new ArrayList<>();
        Cursor c = db.query("students", new String[] {"_id", "name", "score"}, null, null, null, null, null);
        if (c.moveToFirst())
        {
            Student s1 = new Student(c.getInt(0), c.getString(1), c.getInt(2));
            mylist.add(s1);
            while(c.moveToNext())
            {
                Student s = new Student(c.getInt(0), c.getString(1), c.getInt(2));
                mylist.add(s);
            }
        }
        return mylist;
    }

    @Override
    public Student getStudent(int id) {
        Cursor c = db.query("students",new String[]{"_id","name","score"},"_id=?",new String[]{String.valueOf(id)},null,null,null);
        if(c.moveToFirst())
        {
            Student s1 = new Student(c.getInt(0),c.getString(1),c.getInt(2));
            return  s1;
        }

        return null;
    }

    @Override
    public boolean updata(Student s) {
        ContentValues cv = new ContentValues();
        cv.put("name",s.name);
        cv.put("score",s.score);
        db.update("students",cv,"_id=?",new String[]{String.valueOf(s.id)});

//        return false;
        return true;    //原寫法false改true
    }

    @Override
    public boolean delete(int id) {
        db.delete("students", "_id=?", new String[] {String.valueOf(id)});
//        return false;
        return true;

    }
}
