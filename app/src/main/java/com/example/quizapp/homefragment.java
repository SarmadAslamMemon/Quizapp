package com.example.quizapp;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class homefragment extends Fragment {

    private static final int PICK_IMAGE_REQUEST = 1;
    private static final String PREFS_NAME = "MyPrefsFile";
    private static final String IMAGE_URI_KEY = "imageUri";

    private RecyclerView recyclerView;
    private QuestionAdapter adapter;
    private List<modelQuiz> questionList;

    private TextView userNameTextView;
    private String enteredName;
    private ImageView profileImageView;

    public homefragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_homefragment, container, false);

        userNameTextView = view.findViewById(R.id.usernameTextView);
        profileImageView = view.findViewById(R.id.imageView);

        recyclerView = view.findViewById(R.id.recyclerinHome);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        questionList = new ArrayList<>();
        adapter = new QuestionAdapter(requireContext(), questionList);
        recyclerView.setAdapter(adapter);

        fetchData();

        // Check if enteredName is already set
        if (enteredName != null) {
            userNameTextView.setText("Welcome, " + enteredName + "!");
        } else {
            // Retrieve entered name from arguments
            Bundle arguments = getArguments();
            if (arguments != null && arguments.containsKey("enteredName")) {
                enteredName = arguments.getString("enteredName");
                // Set the entered name in the TextView
                userNameTextView.setText("Welcome, " + enteredName + "!");
            }
        }

        // Set click listener for the profile image
        profileImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickImageFromGallery();
            }
        });

        // Load the saved image
        loadSavedImage();

        return view;
    }

    // Method to set user name in the fragment
    public void setUserName(String userName) {
        enteredName = userName;
        if (userNameTextView != null) {
            userNameTextView.setText("Welcome, " + userName + "!");
        }
    }

    private void pickImageFromGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == getActivity().RESULT_OK && data != null && data.getData() != null) {
            Uri imageUri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(requireContext().getContentResolver(), imageUri);
                profileImageView.setImageBitmap(bitmap);

                // Save the image URI
                saveImageUri(imageUri);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void saveImageUri(Uri imageUri) {
        // Save the image URI to SharedPreferences
        SharedPreferences preferences = requireActivity().getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(IMAGE_URI_KEY, imageUri.toString());
        editor.apply();
    }

    private void loadSavedImage() {
        // Load the saved image URI from SharedPreferences
        SharedPreferences preferences = requireActivity().getSharedPreferences(PREFS_NAME, 0);
        String imageUriString = preferences.getString(IMAGE_URI_KEY, null);

        if (imageUriString != null) {
            Uri imageUri = Uri.parse(imageUriString);

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(requireContext().getContentResolver(), imageUri);
                profileImageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void fetchData() {
        String url = "https://roughlyandriodapp.000webhostapp.com/Quiz%20app/RandomQuestions.json";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET, url, null, response -> {
            try {
                JSONArray questionsArray = response.getJSONArray("questions");
                for (int i = 0; i < questionsArray.length(); i++) {
                    JSONObject questionObject = questionsArray.getJSONObject(i);

                    int id = questionObject.getInt("id");
                    String questionText = questionObject.getString("question");
                    JSONArray optionsArray = questionObject.getJSONArray("options");

                    List<String> options = new ArrayList<>();
                    for (int j = 0; j < optionsArray.length(); j++) {
                        options.add(optionsArray.getString(j));
                    }

                    modelQuiz newQuestion = new modelQuiz(id, questionText, options);
                    newQuestion.setId(id);
                    newQuestion.setQuestion(questionText);
                    newQuestion.setOptions(options);

                    questionList.add(newQuestion);
                }

                adapter.notifyDataSetChanged();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        },
                error -> {
                    // Handle error
                    Toast.makeText(requireContext(), "Error fetching data", Toast.LENGTH_SHORT).show();
                }
        );

        // Add the request to the RequestQueue
        RequestQueue requestQueue = Volley.newRequestQueue(requireContext());
        requestQueue.add(jsonObjectRequest);
    }
}
