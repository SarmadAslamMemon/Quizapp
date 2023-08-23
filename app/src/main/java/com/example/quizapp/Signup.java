package com.example.quizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Signup extends AppCompatActivity {

    TextView lgtxt;
    TextInputEditText name,email,phone,password;
    ProgressBar progressbar;
    Button submitbtn;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
            get();
    submitbtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            progressbar.setVisibility(View.VISIBLE);
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
                

                auth=FirebaseAuth.getInstance();
                auth.createUserWithEmailAndPassword(e,ps)
                    .addOnCompleteListener(Signup.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {

                                progressbar.setVisibility(View.INVISIBLE);
                                clearfields();
                                Toast.makeText(getApplicationContext(),"Registered Successfully",Toast.LENGTH_LONG).show();


                            } else {
                                // If sign in fails, display a message to the user.

                                progressbar.setVisibility(View.INVISIBLE);
                                clearfields();
                                Toast.makeText(getApplicationContext(),"unsuccessfull",Toast.LENGTH_LONG).show();
                            }

                        }
                    }) ;

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
        progressbar=findViewById(R.id.progressBar);

    }

    void clearfields(){

        name.setText(" ");
        email.setText(" ");
        phone.setText(" ");
        password.setText(" ");


    }
}