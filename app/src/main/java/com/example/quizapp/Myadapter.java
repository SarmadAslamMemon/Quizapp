package com.example.quizapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Myadapter extends RecyclerView.Adapter<Myadapter.ViewHolder> {
    Context context;
    ArrayList<model>subject;

    public Myadapter(Context context ,ArrayList<model>subject) {
        this.context=context;
        this.subject=subject;
    }

    @NonNull
    @Override
    public Myadapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Myadapter.ViewHolder holder, int position) {
        holder.getTextView().setText(subject.get(position).getSubtitle());
//      int p=holder.getAdapterPosition();
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//
//                if(p==0){
//                        Intent i=new Intent(context, afterfragment.class);
//                        context.startActivity(i);
//
//                }
//            }
//        });


    }

    @Override
    public int getItemCount() {
        return subject.size();
    }

    public static class ViewHolder extends  RecyclerView.ViewHolder{

        TextView textView;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            textView=itemView.findViewById(R.id.subjecttitle);
        }

        public TextView getTextView() {
            return textView;
        }
    }
}