package com.frandog.a20180117_01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.frandog.a20180117_01.data.Student;

import java.util.ArrayList;

public class GetStudent extends AppCompatActivity {
    TextView tv,tv2,tv3;
    Student s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_student);

        tv=findViewById(R.id.textView);
        tv2=findViewById(R.id.textView2);
        tv3=findViewById(R.id.textView3);

        int id = getIntent().getIntExtra("id",0);
        s = MainActivity.dao.getStudent(id);
        tv.setText(String.valueOf(s.id));
        tv2.setText(s.name);
        tv3.setText(String.valueOf(s.score));
    }
}
