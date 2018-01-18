package com.frandog.a20180117_01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.frandog.a20180117_01.data.Student;

public class EditActivity extends AppCompatActivity {
    TextView tv;
    EditText ed1,ed2;
    int id;
    Student s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        id = getIntent().getIntExtra("id",0);
//        String  name = getIntent().getStringExtra("name");    不用這兩行
//        int score = getIntent().getIntExtra("score",0);
        s = MainActivity.dao.getStudent(id);

        tv =  findViewById(R.id.textView4);
        ed1 = findViewById(R.id.editText4);
        ed2 = findViewById(R.id.editText5);

        tv.setText(String.valueOf(s.id));
        ed1.setText(s.name);
        ed2.setText(String.valueOf(s.score));

    }
    public void clickBack2(View v)
    {
        finish();
    }
    public void clickUpdate(View v)
    {
        Student s = new Student(id,ed1.getText().toString(),Integer.valueOf(ed2.getText().toString()));
        MainActivity.dao.updata(s);
        finish();
    }

}
