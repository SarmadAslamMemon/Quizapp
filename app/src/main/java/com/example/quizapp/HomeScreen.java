package com.example.quizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;


public class HomeScreen extends AppCompatActivity {
    BottomNavigationView bottomview;
     homefragment homefragment =new homefragment();


    Profile profilefragment =new Profile();
     leaderboardfragment leaderboardfrag =new leaderboardfragment();

     bookmarkfragment bookmarkfrag =new bookmarkfragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home_screen);
        bottomview=findViewById(R.id.bottomnavigation);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new homefragment()).commit();
        bottomview.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected( MenuItem item) {

                int itemId = item.getItemId();

                if (itemId == R.id.homeicon) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, homefragment).commit();
                    return true;
                }
                else if (itemId == R.id.leaderboardicon) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, leaderboardfrag).commit();
                    return true;
                }
                else if (itemId == R.id.profileicon) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, profilefragment).commit();
                    return true;
                }
                else if (itemId == R.id.bookmarkicon) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, bookmarkfrag).commit();
                    return true;
                }

                return false;
            }
        });


    }

}