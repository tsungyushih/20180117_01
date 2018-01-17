package com.frandog.a20180117_01;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.frandog.a20180117_01.data.Student;

public class AddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
    }
    public void clickadd(View v){
        EditText ed1 = findViewById(R.id.editText);
        EditText ed2 = findViewById(R.id.editText2);
        EditText ed3 = findViewById(R.id.editText3);
        int id = Integer.valueOf(ed1.getText().toString());
        String name = ed2.getText().toString();
        int score = Integer.valueOf(ed3.getText().toString());
        MainActivity.dao.add(new Student(id,name,score));

        finish();       //表示關閉本頁並會回上一頁
    }
}
