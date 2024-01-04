package com.example.quizapp;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

public class Profile extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        // Find the logout button in your layout
        Button logoutButton = view.findViewById(R.id.logoutBtn);

        // Set click listener for the logout button
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle logout functionality
                logout();
            }
        });

        return view;
    }

    private void logout() {
        // Clear the user's login status
        SessionManager sessionManager = new SessionManager(requireContext());
        sessionManager.setLoggedIn(false);
         // Clear stored user name


        // Perform any other logout-related actions

        // Start LoginActivity to initiate the login process
        Intent intent = new Intent(requireContext(), Login.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        requireActivity().finish();  // Optional: Finish the current activity if needed
    }

}
