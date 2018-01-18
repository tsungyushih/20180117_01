package com.frandog.a20180117_01;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.frandog.a20180117_01.data.Student;

import java.util.ArrayList;

import static com.frandog.a20180117_01.MainActivity.dao;

public class GetStudent extends AppCompatActivity {
    TextView tv,tv2,tv3;
    Student s;
    int id;
    boolean fastBack = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_student);

        tv=findViewById(R.id.textView);
        tv2=findViewById(R.id.textView2);
        tv3=findViewById(R.id.textView3);

//        int id = getIntent().getIntExtra("id",0);     不能再寫int，區域變數會取代成員變數，最外層的int會被取代掉，導致後面寫的id都會抓不到id的狀況
        id = getIntent().getIntExtra("id",0);

//        s = dao.getStudent(id);     要放進onResume裡，因為這行是寫在onCreate，所以EditActivity在finish後部會重新抓資料
//        tv.setText(String.valueOf(s.id));   要放進onResume裡，因為這3行是寫在onCreate，所以EditActivity在finish後部會重新抓資料
//        tv2.setText(s.name);
//        tv3.setText(String.valueOf(s.score));
    }

    public void clickBack(View v)
    {
//        Intent it=new Intent(GetStudent.this,MainActivity.class);     //不能用Intent，會一層一層疊上去，會導致按back鍵時會一層一層回去，效果會很奇怪
//        startActivity(it);
        finish();
    }
    public void clickDelete(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(GetStudent.this);
        builder.setTitle("刪除確認");
        builder.setMessage("確認要刪除本筆資料?");
        builder.setPositiveButton("確認", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dao.delete(id);
                finish();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }
    public void clickEdit(View v)
    {
        Intent it = new Intent(GetStudent.this,EditActivity.class);
        it.putExtra("id",id);
//        it.putExtra("name",s.name);       這兩行不用
//        it.putExtra("score",s.score);
        fastBack = true;
        startActivity(it);
    }
    protected void onResume() {
        super.onResume();
        if(fastBack)
        {
            finish();
        }
        s = MainActivity.dao.getStudent(id);
        tv.setText(String.valueOf(s.id));
        tv2.setText(s.name);
        tv3.setText(String.valueOf(s.score));
    }
}
