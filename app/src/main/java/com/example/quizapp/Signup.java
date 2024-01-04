package com.example.quizapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class


Signup extends AppCompatActivity {
    ProgressDialog progressDialog;
    EditText userName,userEmail,userPass,userConfPass;
    TextView nextsignup;
    String url="https://roughlyandriodapp.000webhostapp.com/userSignup.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);




        progressDialog= new ProgressDialog(Signup.this);
        progressDialog.setTitle("Please wait");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);





        userName = findViewById(R.id.userSignUpName);
        userEmail = findViewById(R.id.userSignUpEmail);
        userPass = findViewById(R.id.userSignUpPass);
        userConfPass = findViewById(R.id.userSignUpConfirmPass);
        Button btnSignUp = findViewById(R.id.btnSignUp);


        nextsignup=findViewById(R.id.nextsignup);
        nextsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(Signup.this,Login.class);
                startActivity(i);
                finish();
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = userName.getText().toString();
                String email = userEmail.getText().toString();
                String password = userPass.getText().toString();
                String confirmPassword = userConfPass.getText().toString();

                // Basic validation
                if (name.isEmpty()) {
                    userName.setError("Please enter your name");
                    userName.requestFocus();
                } else if (email.isEmpty() || !email.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")) {
                    userEmail.setError("Please enter a valid email address");
                    userEmail.requestFocus();
                } else if (password.isEmpty() || !password.matches("^(?=.*[A-Z])(?=.*\\d).{6,16}$")) {
                    userPass.setError("Password must contain a capital letter, a number, and be 6 to 16 characters long");
                    userPass.requestFocus();
                } else if (confirmPassword.isEmpty()) {
                    userConfPass.setError("Please confirm your password");
                    userConfPass.requestFocus();
                } else if (!password.equals(confirmPassword)) {
                    userConfPass.setError("Passwords do not match");
                    userConfPass.requestFocus();
                } else {



                    SignUptoDb();

                    Intent i = new Intent(Signup.this,Login.class);
                    i.putExtra("name",name);
                    i.putExtra("pass",password);
                    startActivity(i);
                    userName.setText("");
                    userPass.setText("");
                    userConfPass.setText("");
                    Toast.makeText(Signup.this, "Success", Toast.LENGTH_SHORT).show();





                }
            }
        });
    }




    void SignUptoDb(){

        String name = userName.getText().toString();
        String email = userEmail.getText().toString();
        String password = userPass.getText().toString();

        progressDialog.show();
        StringRequest request = new StringRequest(Request.Method.POST, url, response -> {
            progressDialog.dismiss();






        }, error -> {
            progressDialog.dismiss();
            Toast.makeText(Signup.this, error.getMessage().toString(), Toast.LENGTH_SHORT).show();


        }


        ){
            @Nullable
            @Override
            protected Map<String, String> getParams() {
                Map <String,String> params= new HashMap<>();
                params.put("userName",name);
                params.put("userEmail",email);
                params.put("userPass",password);
                return  params;
            }
        };

        RequestQueue rQueue= Volley.newRequestQueue(Signup.this);
        rQueue.add(request);


    }





}


