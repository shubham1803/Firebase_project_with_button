package com.brillica_services.firebase_project_with_botton;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class add_student extends AppCompatActivity implements AdapterView.OnItemSelectedListener, RecyclerAdapter.ListItemClickListener {

    /*
     * Creating global object of different views that we
     * are going to use in our application.
     * */
    EditText studentNameTF, studentPhoneTF, studentAddressTF;
    Button addStudent;
    Spinner spinnerCollegeNames;
    ProgressBar progressBar;
    LinearLayout addStudentLayout;

    /*
     * Creating a global collegeName String object.*/
    String collegeName = "";

    /*
     * Creating a string of array of colleges*/
    String collegeNames[] =new String[4];

    /*
     * Database Helper*/
    DatabaseHelper databaseHelper;

    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

     /* casting objects with the respective view ids.*/
    studentNameTF = findViewById(R.id.student_name);
    spinnerCollegeNames = findViewById(R.id.college_name_spinner);
    studentPhoneTF = findViewById(R.id.enter_phone);
    studentAddressTF = findViewById(R.id.enter_address);
    addStudent = findViewById(R.id.add_student_button);
    progressBar =findViewById(R.id.recycler_progress_bar);
    addStudentLayout=findViewById(R.id.add_student_linear_layout);

    databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("college").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int i=0;
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    collegeNames[i] = String.valueOf(snapshot.getValue());
                    ++i;
                    }
                progressBar.setVisibility(View.GONE);
                addStudentLayout.setVisibility(View.VISIBLE);
            }
            public void onCancelled
                    (DatabaseError databaseError) {

            }
        });


    databaseHelper = new DatabaseHelper(this);

    /*
     * Using setOnItemSelectedListener on spinner object
     * and giving it the context - this, meaning current activity*/
        spinnerCollegeNames.setOnItemSelectedListener(this);

    /*
     * Creating an arrayAdapter object and passing 3 different arguments
     * i.e. context, layout, array*/
    ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, collegeNames);

    /*
     * using the spinner's setAdapter method to update it's adapter*/
        spinnerCollegeNames.setAdapter(arrayAdapter);

    /*
     * setPrompt is select on spinner to just give the refernce that the
     * first object of array is only a label.*/
        spinnerCollegeNames.setPrompt(collegeNames[0]);


        addStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                 * On the click of the button, getting values from
                 * the user input.*/
                String name = studentNameTF.getText().toString();
                long phone = Long.parseLong(studentPhoneTF.getText().toString());
                String address = studentAddressTF.getText().toString();

                /*
                 * Storing the new values into the arrayList using the
                 * Student class object.*/
                databaseHelper.addNewStudent(new StudentModel(name, collegeName, address, phone));

                /*
                 * Showing a success message once the data has been saved into arrayList*/
                Toast.makeText(getApplicationContext(), "Student data saved successfully", Toast.LENGTH_LONG).show();
            }
        });

}
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        /*
         * getting college name from the list of colleges.
         * It will be updated only on the basis of array.*/
        collegeName = collegeNames[position];
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onListItemClickListener(int clickedItemIndex) {

    }
}
