package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;


public class facts_cardview extends AppCompatActivity {


    Button btnstart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facts_cardview);


    btnstart=findViewById(R.id.btnstart);
    btnstart.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {


            SessionManager sessionManager = new SessionManager((Application) getApplicationContext());

            if (sessionManager.isLoggedIn()) {
                // User is already logged in, load the main content or a fragment
                Intent i =new Intent(facts_cardview.this, HomeScreen.class);
                startActivity(i);
                finish();
            }else {

                Intent i = new Intent(facts_cardview.this, Login.class);
                startActivity(i);
                finish();
            }

        }
    });





    }
}