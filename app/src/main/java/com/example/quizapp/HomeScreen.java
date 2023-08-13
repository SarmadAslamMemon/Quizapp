package com.example.quizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;


public class HomeScreen extends AppCompatActivity {
    BottomNavigationView bottomview;
     homefragment homefrag =new homefragment();
     factfragment factfrag =new factfragment();
     leaderboardfragment leaderboardfrag =new leaderboardfragment();
     quizfragment quizfrag =new quizfragment();
     bookmarkfragment bookmarkfrag =new bookmarkfragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        bottomview=findViewById(R.id.bottomnavigation);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, homefrag).commit();

        bottomview.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected( MenuItem item) {

                int itemId = item.getItemId();

                if (itemId == R.id.homeicon) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, homefrag).commit();
                } else if (itemId == R.id.facticon) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, factfrag).commit();
                } else if (itemId == R.id.leaderboardicon) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, leaderboardfrag).commit();
                } else if (itemId == R.id.quizicon) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, quizfrag).commit();
                } else if (itemId == R.id.bookmarkicon) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, bookmarkfrag).commit();
                }

                return false;
            }
        });


    }

}