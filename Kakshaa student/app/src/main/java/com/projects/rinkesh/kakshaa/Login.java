package com.projects.rinkesh.kakshaa;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Login extends AppCompatActivity {

    private TextView logotext;

    private Typeface logo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        logo = Typeface.createFromAsset(getAssets(), "fonts/logo.TTF");

        logotext = (TextView) findViewById(R.id.logo);

        logotext.setTypeface(logo);


    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void loginbtn(View view) {

        Intent loginIntent = new Intent(this, Dashboard.class);
        startActivity(loginIntent);
    }
}
