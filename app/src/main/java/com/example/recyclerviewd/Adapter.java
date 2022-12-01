package com.example.recyclerviewd;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
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
    public void onBindViewHolder(@NonNull final Adapter.ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
            final Details details =detailsModals.get(position);

        holder.username.setText(details.getName());
        holder.des.setText(details.getDescription());
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String stringName =holder.username.getText().toString();
                String stringdescription =holder.des.getText().toString();
                dbHelper.update(new Details(details.getId(),stringName,stringdescription));
                notifyDataSetChanged();
                ((Activity) context).finish();
                context.startActivity(((Activity)context).getIntent());
            }
        });



        holder.del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            dbHelper.delete(details.getId());
                detailsModals.remove(position);
                notifyDataSetChanged();

            }
        });



    }

    @Override
    public int getItemCount() {
        return detailsModals.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
            TextView username;
            TextView des;
            ImageView edit;
            ImageView del;
            CardView singlerow;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            singlerow = itemView.findViewById(R.id.single_row_main);
            username = itemView.findViewById(R.id.usrname_s);
            des = itemView.findViewById(R.id.des_s);
            del = itemView.findViewById(R.id.delete_btn);
            edit = itemView.findViewById(R.id.edit_btn);

        }
    }
}
