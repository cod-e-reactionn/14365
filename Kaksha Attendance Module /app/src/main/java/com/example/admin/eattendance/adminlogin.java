package com.example.admin.eattendance;

import android.content.Intent;
import android.os.Message;
import android.support.constraint.solver.SolverVariable;
import android.support.constraint.solver.widgets.Snapshot;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class adminlogin extends AppCompatActivity {

    DatabaseReference ref;
    DatabaseReference dbStudent;
    DatabaseReference dbAttendance;

    ArrayList Studentlist = new ArrayList<>();

    String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminlogin);

        ref = FirebaseDatabase.getInstance().getReference();
        dbStudent = ref.child("student");
        dbAttendance = ref.child("attendance");


    }
    public void AddTeacherButton(View v){
        Intent intent = new Intent(this, addteacher.class);
        startActivity(intent);
    }
    public void AddStudentButton(View v){
        Intent intent = new Intent(this, addstudent.class);
        startActivity(intent);
    }
    public void attendanceRecord(View v){
        Intent intent = new Intent(this, admin_attendanceSheet.class);
        startActivity(intent);
    }

    public void CreateAttendance(View v){

        //Toast.makeText(getApplicationContext(),date, Toast.LENGTH_LONG).show();




        dbStudent.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String sid,P1="-",P2="-",P3="-",P4="-",P5="-",P6="-",P7="-",P8="-";
                Attendance_sheet a = new Attendance_sheet(P1,P2,P3,P4,P5,P6,P7,P8);
                // Result will be holded Here
                for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                    sid=dsp.child("sid").getValue().toString(); //add result into array list
                    dbAttendance.child(date).child(sid).setValue(a);

                }
                Toast.makeText(getApplicationContext(),"successfully created "+date+" db", Toast.LENGTH_LONG).show();
            }



            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "something went wrong", Toast.LENGTH_LONG).show();
            }

        });


    }


}
/*firebaseDatabase.getReference("parent")
                            .orderByChild("childNode")
                            .startAt("[a-zA-Z0-9]*")
                            .endAt(searchString)


*/