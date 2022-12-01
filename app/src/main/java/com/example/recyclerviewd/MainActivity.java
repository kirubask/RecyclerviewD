package com.example.recyclerviewd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.recyclerviewd.Modal.Details;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    ImageView plusimg;
    RecyclerView recycle;
    DbHelper myobj;
    Adapter myadapter;
    List<Details> detailsModals ;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        plusimg = findViewById(R.id.plus_image);
        recycle = findViewById(R.id.recycler_view);



        recycle.setLayoutManager(new LinearLayoutManager(this));
        recycle.setHasFixedSize(true);

        myobj = new DbHelper(this);

        detailsModals =myobj.getdata();
        if (detailsModals.size()>0){
            myadapter =new Adapter(detailsModals,MainActivity.this);
            recycle.setAdapter(myadapter);


        }
        else {
            Toast.makeText(this, "NO data avalibale", Toast.LENGTH_SHORT).show();
        }


////        recycle.setAdapter(myadapter);
//        recycle.setLayoutManager(new LinearLayoutManager(this));



        plusimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,user_data_entry.class);
                startActivity(intent);


            }
        });
    }




}