package com.example.quizapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;

public class QuizFragment extends Fragment {

    private ListView questionListView;
    private Button submitFinalButton;
    private TextView finalScoreTextView;

    private String[] questions = {
            "What is the capital of France?",
            "Which planet is known as the Red Planet?",
            "Who wrote 'Romeo and Juliet'?",
            "What is the largest mammal?",
            "What is the currency of Japan?"
    };
    private String[][] options = {
            {"Paris", "Berlin", "London"},
            {"Mars", "Venus", "Jupiter"},
            {"William Shakespeare", "Charles Dickens", "Jane Austen"},
            {"Blue Whale", "Elephant", "Giraffe"},
            {"Yen", "Won", "Ringgit"}
    };
    private int[] correctAnswers = {0, 0, 0, 0, 0}; // Index of correct options for each question
    private SparseArray<RadioGroup> radioGroups = new SparseArray<>(); // To store RadioGroups for each question
    private int[] userAnswers = new int[questions.length];
    private int score = 0;

    public QuizFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_quiz, container, false);

        // Initialize views
        questionListView = view.findViewById(R.id.questionListView);
        submitFinalButton = view.findViewById(R.id.submitFinalButton);
        finalScoreTextView = view.findViewById(R.id.finalScoreTextView);




        AppCompatActivity appCompatActivity = (AppCompatActivity) getActivity();
        QuizQuestionAdapter adapter = new QuizQuestionAdapter(appCompatActivity, questions);

        questionListView.setAdapter(adapter);

        // Set click listener for the final submit button
        submitFinalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitFinal();
            }
        });

        return view;
    }


    private void submitFinal() {
        // Retrieve user-selected answers from the RadioGroups
        for (int i = 0; i < radioGroups.size(); i++) {
            int questionIndex = radioGroups.keyAt(i);
            RadioGroup radioGroup = radioGroups.valueAt(i);

            int selectedOptionIndex = -1;

            // Get the ID of the checked RadioButton
            int checkedRadioButtonId = radioGroup.getCheckedRadioButtonId();

            if (checkedRadioButtonId != -1) {
                // Find the index of the checked RadioButton within the RadioGroup
                selectedOptionIndex = radioGroup.indexOfChild(radioGroup.findViewById(checkedRadioButtonId));
            }

            if (selectedOptionIndex != -1) {
                userAnswers[questionIndex] = selectedOptionIndex;
            } else {
                // If no answer is selected, you may want to handle it accordingly
                // For simplicity, we assume an invalid index (-1) for unanswered questions
                userAnswers[questionIndex] = -1;
            }
        }

        // Calculate the final score
        score = 0;
        for (int i = 0; i < userAnswers.length; i++) {
            if (userAnswers[i] == correctAnswers[i]) {
                score++;
            }
        }

        // Display the final score
        showScoreDialog(score);
        updateFinalScoreText();
    }


    private void showScoreDialog(int score) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Quiz Result");
        builder.setMessage("Your total score: " + score);

        // Add a button to close the dialog
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // You can perform any additional actions here if needed
                dialog.dismiss();
                requireActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, new homefragment(),"homeFragmentTag") // Replace with the actual ID of your fragment container
                        .addToBackStack(null)
                        .commit();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }


        // Update the final score TextView
        private void updateFinalScoreText () {
            int totalCorrectAnswers = 0;
            for (int i = 0; i < userAnswers.length; i++) {
                if (userAnswers[i] == correctAnswers[i]) {
                    totalCorrectAnswers++;
                }
            }
            finalScoreTextView.setText("Total Correct Answers: " + totalCorrectAnswers + "\nTotal Score: " + score);
        }

        // Custom ArrayAdapter for handling the custom layout for each question item in the ListView
        private class QuizQuestionAdapter extends ArrayAdapter<String> {

            QuizQuestionAdapter(@NonNull AppCompatActivity context, String[] questions) {
                super(context, R.layout.list_item_question, questions);
            }

            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                LayoutInflater inflater = getLayoutInflater();
                View itemView = inflater.inflate(R.layout.list_item_question, parent, false);

                // Set question text
                TextView questionText = itemView.findViewById(R.id.questionText);
                questionText.setText(getItem(position));

                // Set up RadioGroup for options
                RadioGroup optionsRadioGroup = itemView.findViewById(R.id.optionsRadioGroup);
                radioGroups.put(position, optionsRadioGroup);

                // Add RadioButtons for each option
                for (int i = 0; i < options[position].length; i++) {
                    RadioButton radioButton = new RadioButton(getActivity());
                    radioButton.setText(options[position][i]);
                    optionsRadioGroup.addView(radioButton);
                }

                return itemView;
            }
        }
    }
