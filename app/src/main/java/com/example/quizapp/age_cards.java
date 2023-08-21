package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.LinearLayout;

public class age_cards extends AppCompatActivity implements View.OnClickListener {

        CardView kids,teen,adult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_age_cards);


        kids=findViewById(R.id.kids_card);
        teen=findViewById(R.id.teenager_card);
        adult=findViewById(R.id.adult_card);
         kids.setOnClickListener(this);
        adult.setOnClickListener(this);
        teen.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        Intent i;

        if (view.getId() == R.id.kids_card){
        i = new Intent(this,Login.class);startActivity(i);
        }
        else if (view.getId() == R.id.teenager_card) {
            i = new Intent(this,Login.class);startActivity(i);
        } else if (view.getId()== R.id.adult_card) {
            i = new Intent(this,Login.class);startActivity(i);
        }
    }
}