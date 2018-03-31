package com.example.admin.eattendance;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class studentlogin extends AppCompatActivity {
String message;
    DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Teacher");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentlogin);

        Bundle bundle = getIntent().getExtras();
        message = bundle.getString("message");
        TextView txtView = (TextView) findViewById(R.id.textView1);


        txtView.setText("welcome "+message);

    }
    public void viewAttendance(View v){
        Bundle basket = new Bundle();
        basket.putString("sid", message);


        Intent intent = new Intent(this, student_attendance_sheet.class);
        intent.putExtras(basket);
        startActivity(intent);
    }
}
