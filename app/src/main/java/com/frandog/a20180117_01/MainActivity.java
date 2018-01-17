package com.frandog.a20180117_01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.frandog.a20180117_01.data.Student;
import com.frandog.a20180117_01.data.StudentScoreDAO;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    final public static StudentScoreDAO dao = new StudentScoreDAO();
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public boolean onCreateOptionsMenu(Menu menu){
//        MenuInflater m=new MenuInflater(this);  //下面那行的另一個寫法，在20180109_01專案時可以，但這專案會失敗，不知道為什麼
//        m.inflate(R.menu.mymenu,menu);          //下面那行的另一個寫法，在20180109_01專案時可以，但這專案會失敗，不知道為什麼
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == R.id.menu_add) {
            Intent it = new Intent(MainActivity.this, AddActivity.class);
            startActivity(it);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {     //MainActivity只是被menu蓋住，所以menu關掉時，MainActivity不會重刷
        super.onResume();
        lv = findViewById(R.id.listview);
        ArrayList<String> studentNames = new ArrayList<>();
        for(Student s:dao.getList())
        {
            studentNames.add(s.name);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1,
                studentNames);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it = new Intent(MainActivity.this,GetStudent.class);
                it.putExtra("id",dao.getList().get(position).id);   //去抓所點擊的位置(position)的list的id
                startActivity(it);
            }
        });
    }


}
