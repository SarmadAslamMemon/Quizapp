package com.example.quizapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {
            TextView signuptxt;
            Button loginbtn;
            ProgressDialog progressDialog;
            EditText userNameTextView,passwordTextView;
            String url="https://roughlyandriodapp.000webhostapp.com/userLogin.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);




        progressDialog= new ProgressDialog(Login.this);
        progressDialog.setTitle("Please wait");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);



        signuptxt=findViewById(R.id.nextsignup);
        loginbtn=findViewById(R.id.btnlogin);
         userNameTextView = findViewById(R.id.userloginname);
         passwordTextView = findViewById(R.id.userloginpass);

        loginbtn.setOnClickListener(v -> {

            String name = userNameTextView.getText().toString();

            String password = passwordTextView.getText().toString();


            // Basic validation
            if (name.isEmpty()) {
                userNameTextView.setError("Please enter your name");
                userNameTextView.requestFocus();
            } else if (password.isEmpty()) {
                passwordTextView.setError("Please enter your password");
                passwordTextView.requestFocus();
            }
            else{


                LoginToDb();
                SessionManager sessionManager = new SessionManager(getApplicationContext());
                sessionManager.setLoggedIn(true);
            }


        });





        Intent i = getIntent();
        if (i.hasExtra("name")  || i.hasExtra("pass")) {
            String userName = i.getStringExtra("name");
            String password = i.getStringExtra("pass");
            userNameTextView.setText(userName);
            passwordTextView.setText(password);

         //   Log.e("errorchk", userName + "final");
        }
        signuptxt.setOnClickListener(view -> {
            Intent inn = new Intent(Login.this,Signup.class);
            startActivity(inn);
            finish();
        });
    }

    private void LoginToDb() {

            String name = userNameTextView.getText().toString();
            String password = passwordTextView.getText().toString();

            progressDialog.show();
            StringRequest request = new StringRequest(Request.Method.POST, url, response -> {



                if(response.equals("success")) {
                    progressDialog.dismiss();
                    Toast.makeText(this, String.valueOf(response), Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(Login.this, HomeScreen.class);
                    startActivity(i);
                    finish();
                }
                else if(response.equals("failure")) {
                    progressDialog.dismiss();

                    Toast.makeText(this, "Invalid UserName or Password !", Toast.LENGTH_SHORT).show();
                }





            }, error -> {
                progressDialog.dismiss();
                Toast.makeText(Login.this, error.getMessage().toString(), Toast.LENGTH_SHORT).show();


            }


            ){
                @Nullable
                @Override
                protected Map<String, String> getParams() {
                    Map <String,String> params= new HashMap<>();
                    params.put("userName",name);
                    params.put("userPass",password);
                    Log.e("checkname",name+password+"");
                    return  params;
                }
            };

            RequestQueue rQueue= Volley.newRequestQueue(Login.this);
            rQueue.add(request);


        }
    }
