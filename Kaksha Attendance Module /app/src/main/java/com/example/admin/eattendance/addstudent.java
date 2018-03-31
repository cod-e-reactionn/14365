package com.example.admin.eattendance;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class addstudent extends AppCompatActivity {
    EditText Sname;
    EditText Sid,spassword;
    String sname,sid,classname,spass;
    Spinner classes;
    DatabaseReference databaseStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addstudent);

        databaseStudent = FirebaseDatabase.getInstance().getReference("student");

        Sname =  (EditText) findViewById(R.id.editText1);
        Sid =  (EditText) findViewById(R.id.editText3);
        classes = (Spinner) findViewById(R.id.spinner3);
        spassword = (EditText) findViewById(R.id.editText4);


       /* addButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                addTeacher();
            }
        });*/
    }

    public void addStudent(View v){


        if (!(TextUtils.isEmpty(Sid.getText().toString()))) {
            //String id = databaseStudent.push().getKey();
            sname = Sname.getText().toString();
            sid = Sid.getText().toString();
            classname = classes.getSelectedItem().toString();
            spass = spassword.getText().toString();

            Student student =new Student(sname ,sid,classname,spass );
            databaseStudent.child(sid).setValue(student);
            Toast.makeText(getApplicationContext(),"student added successfully", Toast.LENGTH_LONG).show();

        }else {
            Toast.makeText(getApplicationContext(),"fields cannot be empty", Toast.LENGTH_LONG).show();
        }
    }
    public void removeStudent(View v){
        if (!TextUtils.isEmpty(Sid.getText().toString())) {
            sid = Sid.getText().toString();
            databaseStudent.child(sid).setValue(null);
            Toast.makeText(getApplicationContext(),"teacher removed successfully", Toast.LENGTH_LONG).show();

        }else {
            Toast.makeText(getApplicationContext(),"id cannot be empty", Toast.LENGTH_LONG).show();
        }
    }


}
