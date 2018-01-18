package com.frandog.a20180117_01.data;

import java.util.ArrayList;

/**
 * Created by Student on 2018/1/17.
 */
//      DAO :（Data Access Objects) 数据访问对象
public class StudentScoreDAO implements StudentDAO {
    public ArrayList<Student> mylist;
    public StudentScoreDAO()
    {
        mylist=new ArrayList<>();
    }
    public boolean add(Student s)
    {
        mylist.add(s);
        return true;
    }
    public ArrayList<Student> getList()
    {
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

    public boolean updata(Student s)    //之所以設成boolean而不是void，是因為要預防當更新失敗時(例如網路不穩)，該走額外的步驟
    {
        for (Student t:mylist)
        {
            if(t.id == s.id)
            {
                t.name = s.name;
                t.score = s.score;
                return true;
            }
        }
        return false;
    }
    public boolean delete(int id)
    {
        for(int i=0;i<mylist.size();i++)    //是<而不是<=
        {
            if(mylist.get(i).id == id)
            {
                mylist.remove(i);
                return true;
            }
        }
        return false;
    }
}
