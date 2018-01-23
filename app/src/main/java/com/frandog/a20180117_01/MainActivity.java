package com.frandog.a20180117_01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.frandog.a20180117_01.data.DBType;
import com.frandog.a20180117_01.data.Student;
import com.frandog.a20180117_01.data.StudentDAO;
import com.frandog.a20180117_01.data.StudentDAOFactory;
import com.frandog.a20180117_01.data.StudentFileDAO;
import com.frandog.a20180117_01.data.StudentScoreDAO;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
//    final public static StudentScoreDAO dao = new StudentScoreDAO();      加入StudentFileDAO後要改成下面那行
//    public static StudentFileDAO dao;     新增StudentDAO後改成下面那行
    public static StudentDAO dao;
//    int dbType; //切換存取位置用     //新增DBType後改成如下
    DBType dbType;
    ListView lv;

    ArrayList<String> studentNames;     //為了連雲端的更動，把這行從區域變成成員變數
    ArrayAdapter<String> adapter;       //為了連雲端的更動，把這行從區域變成成員變數

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        dao = new StudentFileDAO(this);    //複寫了建構式StudentFileDAO  //加了dbType後，又改成下面兩行
//        dbType = 1; // 1:記憶體 2:檔案     //新增DBType後改成如下


//        dbType = DBType.FILE;
//        dbType = DBType.DB;     //教完20180122_02SQLite資料庫後，將此專案改成寫入SQL裡
        dbType = DBType.CLOUD;     //教完20180122_02資料庫後，將此專案改成寫入雲端裡


        dao = StudentDAOFactory.getDAOInstance(this, dbType);

        studentNames = new ArrayList<>();
        adapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1, studentNames);
        lv = (ListView) findViewById(R.id.listview);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent it = new Intent(MainActivity.this, GetStudent.class);
                it.putExtra("id", dao.getList().get(position).id);
                startActivity(it);
            }
        });
    }


    //    連資料庫後的調整
    @Override
    protected void onResume() {
        super.onResume();
        refreshData();
    }
    public void refreshData()
    {
        studentNames.clear();
        for (Student s : dao.getList())
        {
            studentNames.add(s.name);
        }
        adapter.notifyDataSetChanged();
    }


    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater m=new MenuInflater(this);  //下面那行的另一個寫法，在20180109_01專案時可以，但這專案會失敗，不知道為什麼
//        m.inflate(R.menu.mymenu,menu);          //下面那行的另一個寫法，在20180109_01專案時可以，但這專案會失敗，不知道為什麼
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_add) {
            Intent it = new Intent(MainActivity.this, AddActivity.class);
            startActivity(it);
        }
        return super.onOptionsItemSelected(item);
    }
}







//因為連雲端時，發現重開程式resume時沒有抓到資料，所以大改
//    @Override
//    protected void onResume() {     //MainActivity只是被menu蓋住，所以menu關掉時，MainActivity不會重刷
//        super.onResume();
//        lv = findViewById(R.id.listview);
//        ArrayList<String> studentNames = new ArrayList<>();
//        for(Student s:dao.getList())
//        {
//            studentNames.add(s.name);
//        }
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,
//                android.R.layout.simple_list_item_1,
//                studentNames);
//        lv.setAdapter(adapter);
//
//        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent it = new Intent(MainActivity.this,GetStudent.class);
//                it.putExtra("id",dao.getList().get(position).id);   //去抓所點擊的位置(position)的list的id
//                startActivity(it);
//            }
//        });
//    }




