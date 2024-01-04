package com.example.quizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeScreen extends AppCompatActivity {
    BottomNavigationView bottomview;
    SessionManager sessionManager;
    homefragment homefragment = new homefragment();
    Profile profilefragment = new Profile();
    QuizFragment leaderboardfrag = new QuizFragment();
    bookmarkfragment bookmarkfrag = new bookmarkfragment();

    private EditText userNickNameEditText;
    private Button submitButton;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dialog = new Dialog(this, R.style.AnimatedDialog);
        sessionManager = new SessionManager(this);


        showCustomDialog();
        if(!sessionManager.getStoredUserName().isEmpty())
        {
          dialog.dismiss();
            homefragment.setUserName(sessionManager.getStoredUserName());
        }



        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home_screen);
        bottomview = findViewById(R.id.bottomnavigation);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, homefragment, "homeFragmentTag").commit();
        bottomview.setOnItemSelectedListener(item -> {

            int itemId = item.getItemId();

            if (itemId == R.id.homeicon) {
                getSupportFragmentManager().beginTransaction().replace(R.id.container, homefragment, "homeFragmentTag").commit();

                return true;
            } else if (itemId == R.id.leaderboardicon) {
                getSupportFragmentManager().beginTransaction().replace(R.id.container, leaderboardfrag).commit();
                return true;
            } else if (itemId == R.id.profileicon) {
                getSupportFragmentManager().beginTransaction().replace(R.id.container, profilefragment).commit();
                return true;
            } else if (itemId == R.id.bookmarkicon) {
                getSupportFragmentManager().beginTransaction().replace(R.id.container, bookmarkfrag).commit();
                return true;
            }

            return false;
        });
    }

    private void showCustomDialog() {
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.custom_input_dialog);
        Window window = dialog.getWindow();
        window.setBackgroundDrawableResource(android.R.color.transparent);

        EditText editTextName = dialog.findViewById(R.id.userNickName);
        Button btnSubmit = dialog.findViewById(R.id.btnUserNameSubmit);

        btnSubmit.setOnClickListener(v -> {
            // Get the entered name
            String enteredName = editTextName.getText().toString();

            if (!enteredName.isEmpty()) {
                Toast.makeText(HomeScreen.this, enteredName, Toast.LENGTH_SHORT).show();

                // Update the existing homefragment with the entered name
                homefragment = (homefragment) getSupportFragmentManager().findFragmentByTag("homeFragmentTag");
                if (homefragment != null) {
                    homefragment.setUserName(enteredName);
                }

                // Store the user name and set the isLoggedIn flag
                sessionManager.storeUserName(enteredName);
                sessionManager.setLoggedIn(true);

                dialog.dismiss();
            } else {
                Toast.makeText(HomeScreen.this, "Please enter any name", Toast.LENGTH_SHORT).show();
            }
        });

        // Show the dialog
        dialog.show();
    }
}
