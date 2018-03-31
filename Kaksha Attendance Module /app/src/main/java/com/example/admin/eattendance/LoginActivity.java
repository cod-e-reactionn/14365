package com.example.admin.eattendance;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class LoginActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText username,password;
    String item;
    String userid,pass;
    DatabaseReference ref;
    String dbpassword;
    Bundle basket;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        //retrieving student id from firebase



        username =  (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.editText2);


        // Spinner element
        Spinner spinner = (Spinner) findViewById(R.id.spinner);

        // Spinner click listener
        spinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("admin");
        categories.add("teacher");
        categories.add("student");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);

    }




    @Override

    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item

        item = parent.getItemAtPosition(position).toString();



        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }


    public void onButtonClick(View v) {

        userid = username.getText().toString();
        pass = password.getText().toString();

        basket = new Bundle();
        basket.putString("message", userid);

        ref = FirebaseDatabase.getInstance().getReference();
        DatabaseReference dbuser = ref.child(item).child(userid);

        dbuser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String dbchild = null;
                if (item == "admin") {
                    dbpassword = dataSnapshot.getValue(String.class);
                    verify(dbpassword);
                } else {
                    if (item == "student") {
                        dbchild = "spass";
                    }
                    if (item == "teacher") {
                        dbchild = "tpass";
                    }

                    dbpassword = dataSnapshot.child(dbchild).getValue(String.class);
                    verify(dbpassword);
                    //do what you want with the email
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "something went wrong", Toast.LENGTH_LONG).show();
            }
        });
    }
        //Toast.makeText(getApplicationContext(),dbpassword, Toast.LENGTH_LONG).show();

     public void verify(String dbpassword){
        if(userid.isEmpty()) {
            Toast.makeText(getApplicationContext(),"username cannot be empty", Toast.LENGTH_LONG).show();
        }
        else
        if (item == "teacher" && pass.equalsIgnoreCase(this.dbpassword)) {


                Intent intent = new Intent(this, teacherlogin.class);
                intent.putExtras(basket);
                startActivity(intent);

        }

        else if (item == "admin" && pass.equalsIgnoreCase(this.dbpassword) ) {
          //  if (userid.equalsIgnoreCase("admin") && pass.equals("admin")) {
                Intent intent = new Intent(this, adminlogin.class);
                intent.putExtras(basket);
                startActivity(intent);
          //  }
        }
        else if (item == "student" && pass.equalsIgnoreCase(this.dbpassword)) {

            Intent intent = new Intent(this, studentlogin.class);
            intent.putExtras(basket);
            startActivity(intent);
        }
        else if(! pass.equalsIgnoreCase(this.dbpassword)){
            Toast.makeText(getApplicationContext(),"userid or password is incorrect", Toast.LENGTH_LONG).show();

        }

    }


}
