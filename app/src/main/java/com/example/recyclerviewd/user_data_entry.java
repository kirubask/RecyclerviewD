package com.example.recyclerviewd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.recyclerviewd.Modal.Details;

public class user_data_entry extends AppCompatActivity {
    EditText user_box;
    EditText descri_box;
    Button add_list_btnn;
    DbHelper myobj;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_data_entry);

        user_box = findViewById(R.id.usr_name);
        descri_box = findViewById(R.id.description_edit);
        add_list_btnn = findViewById(R.id.add_list_btn);

        myobj = new DbHelper(this);



        add_list_btnn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uname =user_box.getText().toString();
                String des =descri_box.getText().toString();
                if (uname == null  || des==null){

                    Toast.makeText(user_data_entry.this, "please enter details", Toast.LENGTH_SHORT).show();
                }

                else{
                    DbHelper dbHelper =new DbHelper(user_data_entry.this);
                    Integer id = null;
                    Details details = new Details(id,uname,des);
                  dbHelper.add(details);
                    Toast.makeText(user_data_entry.this, "added sucessfully", Toast.LENGTH_SHORT).show();

                    startActivity(getIntent());
                    Intent intent = new Intent(user_data_entry.this,MainActivity.class);
                    startActivity(intent);
                    finish();


                }



            }
        });





    }
}