package com.brillica_services.firebase_project_with_botton;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class display extends AppCompatActivity implements  RecyclerAdapter.ListItemClickListener  {
    RecyclerView recyclerView;

    StudentModel studentModel;
    DatabaseHelper databaseHelper;

    RecyclerAdapter recyclerAdapter;

    ArrayList<StudentModel> studentModelArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        recyclerView = findViewById(R.id.recycler_view_sample);
        databaseHelper = new DatabaseHelper(this);
        studentModelArrayList.addAll(databaseHelper.allStudentsDetails());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setHasFixedSize(true);
        recyclerAdapter = new RecyclerAdapter(studentModelArrayList,this);

        recyclerView.setAdapter(recyclerAdapter);
    }

    @Override
    public void onListItemClickListener(int clickedItemIndex) {
        Intent intent=new Intent(display.this,details.class);
        intent.putExtra("id",studentModelArrayList.get(clickedItemIndex).id);
        intent.putExtra("name",studentModelArrayList.get(clickedItemIndex).name);
        intent.putExtra("cllgname",studentModelArrayList.get(clickedItemIndex).collegeName);
        intent.putExtra("phone",studentModelArrayList.get(clickedItemIndex).phoneNumber);
        intent.putExtra("address",studentModelArrayList.get(clickedItemIndex).address);
        startActivity(intent);
    }
}
