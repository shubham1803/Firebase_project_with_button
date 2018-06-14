package com.brillica_services.firebase_project_with_botton;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button addbtn, displayall;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addbtn=(Button)findViewById(R.id.addbtn);
        displayall=(Button)findViewById(R.id.display_all);


        /* Adding a click listener on add*/
        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, add_student.class);
                startActivity(intent);

            }
        });
        displayall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, display.class);
                startActivity(intent);
            }
        });


    }

}