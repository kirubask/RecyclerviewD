package com.example.recyclerviewd;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerviewd.Modal.Details;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

     Context context;

      List<Details> detailsModals;
    DbHelper dbHelper;


   public Adapter(   List<Details> detailsModals,Context context ){
//        this.activity = activity;
        this.context = context;
        this.detailsModals =detailsModals;

       dbHelper = new DbHelper(context);

    }



    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.single_row, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
            final Details details =detailsModals.get(position);

        holder.username.setText(details.getName());
        holder.des.setText(details.getDescription());


    }

    @Override
    public int getItemCount() {
        return detailsModals.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
            TextView username;
            TextView des;
            CardView singlerow;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            singlerow = itemView.findViewById(R.id.single_row_main);
            username = itemView.findViewById(R.id.usrname_s);
            des = itemView.findViewById(R.id.des_s);
        }
    }
}
