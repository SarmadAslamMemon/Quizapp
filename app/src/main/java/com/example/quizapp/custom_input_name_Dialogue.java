package com.example.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class custom_input_name_Dialogue extends AppCompatActivity {


    private EditText userNickNameEditText;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize views
        userNickNameEditText = findViewById(R.id.userNickName);
        submitButton = findViewById(R.id.btnUserNameSubmit);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userInput = userNickNameEditText.getText().toString();


                if (!userInput.isEmpty()) {

                    Toast.makeText(custom_input_name_Dialogue.this, "Welcome, " + userInput + "!", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(custom_input_name_Dialogue.this, homefragment.class);
                    startActivity(i);

                } else {
                    // Display an error message if the user did not enter a name
                    Toast.makeText(custom_input_name_Dialogue.this, "Please enter your name.", Toast.LENGTH_SHORT).show();
                }
            }


        });
    }

}
