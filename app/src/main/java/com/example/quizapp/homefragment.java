package com.example.quizapp;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import javax.security.auth.Subject;


public class homefragment extends Fragment {


  RecyclerView recyclerView ;
  ArrayList<model>subjects;

    CardView subfactcard;
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {





        View view = inflater.inflate(R.layout.fragment_homefragment, container, false);
        subjects=new ArrayList<>();
        subjects.add(new model("Physics"));
        subjects.add(new model("Chemistry"));
        subjects.add(new model("Biology"));
        subjects.add(new model("Maths"));
        subjects.add(new model("English"));
        subjects.add(new model("History"));
        subjects.add(new model("Physics"));
        subjects.add(new model("Physics"));


        recyclerView=view.findViewById(R.id.recycler);

        Myadapter myadapter = new Myadapter(view.getContext(),subjects);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);


        recyclerView.setAdapter(myadapter);
        return view;





    }
}