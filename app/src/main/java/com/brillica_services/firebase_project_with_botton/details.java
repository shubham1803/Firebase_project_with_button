package com.brillica_services.firebase_project_with_botton;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class details extends AppCompatActivity {

    TextView displaytv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        displaytv=(TextView)findViewById(R.id.result);
        displaytv.setText("Name: "+getIntent().getExtras().getString("name")+
                "\nId: "+getIntent().getExtras().getInt("id")+
                "\nCollege Name: "+getIntent().getExtras().getString("cllgname")+
                "\nPhone Number:"+getIntent().getExtras().getLong("phone")+
                "\nAddress:"+getIntent().getExtras().getString("address"));

    }
}
