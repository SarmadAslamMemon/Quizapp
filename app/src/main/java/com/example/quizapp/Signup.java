package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

public class Signup extends AppCompatActivity {

    TextView lgtxt;
    TextInputEditText name,email,phone,password;
    Button submitbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
    get();

    submitbtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String n = name.getText().toString();
            String e = email.getText().toString();
            String ph = phone.getText().toString();
            String ps = password.getText().toString();

            if (n.isEmpty()) {
                name.setError("Name is required");

            }
            else if (e.isEmpty()) {
                email.setError("Email is required!");
            }

            else if (ph.isEmpty()) {
                phone.setError("Phone is required!");
            }

            else if (ps.isEmpty()) {
                password.setError("Password required");
            }
            else if (ps.length()< 6 || ps.length()> 12)
            {
                password.setError("Invalid length ");
            }
            else {
                Intent i = new Intent(Signup.this, Login.class);
                startActivity(i);
                finish();
            }
        }

    });
        lgtxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Signup.this,Login.class);
                startActivity(i);
            }
        });

    }


    void get(){
        lgtxt=findViewById(R.id.lgtxt);
        name=findViewById(R.id.sname);
        email=findViewById(R.id.semail);
        phone=findViewById(R.id.sphone);
        password=findViewById(R.id.spassword);
        submitbtn=findViewById(R.id.btn_signup);

    }
}