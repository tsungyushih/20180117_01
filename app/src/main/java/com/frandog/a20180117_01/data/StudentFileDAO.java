package com.frandog.a20180117_01.data;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

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
//      步驟:把StudentScoreDAO複製貼上，寫saveFile和load(要用到Gson的Gradle)，修改StudentFileDAO，修改MAinActivity，把save和load加進每項DAO的內容裡
public class StudentFileDAO implements StudentDAO {
    public ArrayList<Student> mylist;
    Context context;        //因為我們要用的getFilesDir是AppCompatActivity 繼承 Context而來的，所以這邊要起一個Context成員變數
    public StudentFileDAO(Context context)
    {
       this.context = context;
        mylist=new ArrayList<>();
    }
    public boolean add(Student s)
    {
        mylist.add(s);
        saveFile();
        return true;
    }
    public ArrayList<Student> getList()
    {
        load();
        return mylist;
    }
    public Student getStudent(int id){
        load();
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
        load();
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
        load();
        for(int i=0;i<mylist.size();i++)    //是<而不是<=
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
        File f = new File(context.getFilesDir(),"mydata.txt");
        FileWriter fw = null;
        try {
            fw = new FileWriter(f);
            Gson gson = new Gson();
            String data = gson.toJson(mylist);
            fw.write(data);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void load()
    {
        File f = new File(context.getFilesDir(),"mydata.txt");
        FileReader fr = null;
        try {
            fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String str = br.readLine();
            Gson gson = new Gson();
            mylist = gson.fromJson(str,new TypeToken<ArrayList<Student>>(){}.getType());
            br.close();
            fr.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
