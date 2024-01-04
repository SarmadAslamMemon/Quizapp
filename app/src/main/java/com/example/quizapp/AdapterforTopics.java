package com.example.quizapp;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterforTopics extends RecyclerView.Adapter<AdapterforTopics.ViewHolder> {
    static Context context;
    ArrayList<model>subject;

    public AdapterforTopics(Context context , ArrayList<model>subject) {
        this.context=context;
        this.subject=subject;
    }

    @NonNull
    @Override
    public AdapterforTopics.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.topiccard,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterforTopics.ViewHolder holder, int position) {
        holder.getTextView().setText(subject.get(position).getSubtitle());
      int p=holder.getAdapterPosition();

        holder.itemView.setOnClickListener(view -> {
            // Handle click event here
            model clickedModel = subject.get(position);
            String subtitle = clickedModel.getSubtitle();
            Toast.makeText(context, subtitle, Toast.LENGTH_SHORT).show();

            // Example: Navigate to another activity
            Intent intent = new Intent(context,PhysicsQuestions.class);
            intent.putExtra("SUBTITLE", subtitle);
            context.startActivity(intent);
        });


    }

    @Override
    public int getItemCount() {
        return subject.size();
    }

    public static class ViewHolder extends  RecyclerView.ViewHolder{

        TextView textView;
        View rootview;
        CardView topiccard;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rootview=itemView;
            textView=itemView.findViewById(R.id.subjecttitle);
            topiccard=itemView.findViewById(R.id.list_item);


            topiccard.setOnClickListener(view -> {

                String topicText = textView.getText().toString();
                if(topicText.equalsIgnoreCase("Physics"))
                {
                    Intent intent = new Intent(context,PhysicsQuestions.class);
                    context.startActivity(intent);
                }else if(topicText.equalsIgnoreCase("English"))
                {
                    Intent intent = new Intent(context,English.class);
                    context.startActivity(intent);
                }else if(topicText.equalsIgnoreCase("Chemistry"))
                {
                    Intent intent = new Intent(context,Chemistry.class);
                    context.startActivity(intent);
                }else if(topicText.equalsIgnoreCase("Biology"))
                {
                    Intent intent = new Intent(context,Biology.class);
                    context.startActivity(intent);
                }else if(topicText.equalsIgnoreCase("Maths"))
                {
                    Intent intent = new Intent(context,Maths.class);
                    context.startActivity(intent);
                }else if(topicText.equalsIgnoreCase("History"))
                {
                    Intent intent = new Intent(context,History.class);
                    context.startActivity(intent);
                }



                // Example: Navigate to another activity
              
            });



        }

        public TextView getTextView() {
            return textView;
        }
    }
}