package com.frandog.a20180117_01.data;

import android.content.Context;

import com.frandog.a20180117_01.MainActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Student on 2018/1/18.
 */
//      改成雲端儲存:點上方tools的firebase/realtime連線，把StudentFileDAO複製貼上，再修改saveFile、建構式等內容，並將load()拿掉，因為建構式已改成addValueEventListener監聽，雲端資料一改就會更新，自然就不會需要load了
public class StudentCloudDAO implements StudentDAO {
    public ArrayList<Student> mylist;
    Context context;
    FirebaseDatabase database;
    DatabaseReference myRef;


    public StudentCloudDAO(final Context context)
    {
       this.context = context;

        mylist = new ArrayList<>();     //當從雲端整筆刪除時，會造成開機就當的bug(mylist連抓兩次造成第2次是null)，所以新增此行

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("scores");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Gson gson = new Gson();
                mylist = gson.fromJson(value, new TypeToken<ArrayList<Student>>(){}.getType());

                if(mylist == null)      //當從雲端整筆刪除時，會造成開機就當的bug(mylist連抓兩次造成第2次是null)，所以新增此行
                {
                    mylist = new ArrayList<>();
                }

                ((MainActivity)context).refreshData();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });
//當從雲端整筆刪除時，會造成開機就當的bug(mylist連抓兩次造成第2次是null)，所以把本行丟上去
//        單步除錯教學:點擊左邊點出紅點，點上方 Run/Record Espresso Test，再統地方選Step Into(F7)
//        if (mylist == null)
//        {
//            mylist = new ArrayList<>();
//        }
    }


    public boolean add(Student s)
    {
        if(mylist == null)
        {
            mylist = new ArrayList<>();     //如果雲端沒資料時，要新增，不然會當機
        }

        mylist.add(s);
        saveFile();
        return true;
    }
    public ArrayList<Student> getList()
    {
//        load();     //  load已刪除
        return mylist;
    }
    public Student getStudent(int id){
        for(Student s :mylist)
        {
            if(s.id == id)
            {
                return  s;
            }
        }
        return null;
    }

    public boolean updata(Student s)
    {
        for (Student t:mylist)
        {
            if(t.id == s.id)
            {
                t.name = s.name;
                t.score = s.score;
                saveFile();
                return true;
            }
        }
        return false;
    }

    public boolean delete(int id)
    {
        for(int i=0;i<mylist.size();i++)
        {
            if(mylist.get(i).id == id)
            {
                mylist.remove(i);
                saveFile();
                return true;
            }
        }
        return false;
    }






    private void saveFile()
    {
        //將tools/firebase的步驟貼上
        // Write a message to the database
        Gson gson = new Gson();
        String data = gson.toJson(mylist);


        myRef.setValue(data);
    }

}
