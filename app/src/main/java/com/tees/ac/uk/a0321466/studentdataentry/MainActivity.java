package com.tees.ac.uk.a0321466.studentdataentry;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public databaseHelper databasehandler;

    //defined view components

    EditText et_name, et_age, et_studentId;
    studentModule studentmodel;
    ListView lv_StudentEntries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databasehandler =new databaseHelper(MainActivity.this);
        //view components reference
        et_name= findViewById(R.id.et_name);
        et_age= findViewById(R.id.et_age);
        et_studentId= findViewById(R.id.et_id);
        lv_StudentEntries=findViewById(R.id.lv_databaseList);

        //add button (add student contacts to the database)
    ((Button)findViewById(R.id.btnAdd)).setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
           //check validation (edit text should not empty)
            try{
                studentmodel =new studentModule(-1,(et_name.getText().toString()),(Integer.parseInt(et_age.getText().toString())),
                        (long) Integer.parseInt(et_studentId.getText().toString()));
                //return true if data successfully inserted to the database
                if (databasehandler.insertContact(studentmodel)) {
                    Toast.makeText(MainActivity.this, "successfully insert data to database", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Error to insert data to database", Toast.LENGTH_SHORT).show();
                }
            }
            catch(Exception err){
                Toast.makeText(MainActivity.this,err.toString(), Toast.LENGTH_SHORT).show();
            }

        }
    });

        //View All student contacts data from the database
        ((Button)findViewById(R.id.btnViewAll)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<studentModule> contactsData = databasehandler.viewAll();
                //this adapter and "simple_list_item_1" are already available in the android
                //if u want to diplay list according to our requirment we can create custom adapter and android.R.layout.nameOfFile
                ArrayAdapter adapter= new ArrayAdapter<studentModule>(MainActivity.this, android.R.layout.simple_list_item_1,contactsData);
                //pass adapter to listview
                lv_StudentEntries.setAdapter(adapter);

//                if (contactsData.size() > 0) {
//                    String studentId = contactsData.get(0).getStudentId().toString();
//                    Toast.makeText(MainActivity.this,studentId, Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(MainActivity.this, "error to get data", Toast.LENGTH_SHORT).show();
//                }
            }
        });


    }
}
