/*
Author: Thanos Moschou
Description: This is a simple fitness app.
 */

package com.example.ufit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;


public class CentralScreenActivity extends AppCompatActivity
{
    private RecyclerView exercisesRecyclerView;
    private CardView fullBodyCard, upperBodyCard, lowerBodyCard, handsCard;
    private ExerciseList exerciseList;
    private ExerciseListAdapter exerciseListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_central_screen);

        fullBodyCard = findViewById(R.id.fullBodyCard);
        upperBodyCard = findViewById(R.id.upperBodyCard);
        lowerBodyCard = findViewById(R.id.lowerBodyCard);
        handsCard = findViewById(R.id.handsCard);

        exerciseList = new ExerciseList(getAssets());

        exerciseListAdapter = new ExerciseListAdapter(CentralScreenActivity.this);
        exerciseListAdapter.setExercises(exerciseList.getAvailableExercises());

        exercisesRecyclerView = findViewById(R.id.exercisesRecyclerView);
        exercisesRecyclerView.setAdapter(exerciseListAdapter);
        exercisesRecyclerView.setLayoutManager(new LinearLayoutManager(CentralScreenActivity.this));

        fullBodyCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView muscleGroup = view.findViewById(R.id.fullBodyTextView);
                String muscleGroupAsAString = muscleGroup.getText().toString();

                modifyExerciseList(muscleGroupAsAString);

                //change the first and revert all the next card view backgrounds
                changeCardViewBackground(fullBodyCard, findViewById(R.id.fullBodyTextView), getResources().getColor(R.color.white_transparent), getResources().getColor(R.color.black));
                changeCardViewBackground(upperBodyCard, findViewById(R.id.upperBodyTextView), getResources().getColor(R.color.grey_transparent), getResources().getColor(R.color.white));
                changeCardViewBackground(lowerBodyCard, findViewById(R.id.lowerBodyTextView), getResources().getColor(R.color.grey_transparent), getResources().getColor(R.color.white));
                changeCardViewBackground(handsCard, findViewById(R.id.handsTextView), getResources().getColor(R.color.grey_transparent), getResources().getColor(R.color.white));

            }
        });

        upperBodyCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView muscleGroup = view.findViewById(R.id.upperBodyTextView);
                String muscleGroupAsAString = muscleGroup.getText().toString();

                modifyExerciseList(muscleGroupAsAString);

                changeCardViewBackground(upperBodyCard, findViewById(R.id.upperBodyTextView), getResources().getColor(R.color.white_transparent), getResources().getColor(R.color.black));
                changeCardViewBackground(fullBodyCard, findViewById(R.id.fullBodyTextView), getResources().getColor(R.color.grey_transparent), getResources().getColor(R.color.white));
                changeCardViewBackground(lowerBodyCard, findViewById(R.id.lowerBodyTextView), getResources().getColor(R.color.grey_transparent), getResources().getColor(R.color.white));
                changeCardViewBackground(handsCard, findViewById(R.id.handsTextView), getResources().getColor(R.color.grey_transparent), getResources().getColor(R.color.white));

            }
        });

        lowerBodyCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView muscleGroup = view.findViewById(R.id.lowerBodyTextView);
                String muscleGroupAsAString = muscleGroup.getText().toString();

                modifyExerciseList(muscleGroupAsAString);

                changeCardViewBackground(lowerBodyCard, findViewById(R.id.lowerBodyTextView), getResources().getColor(R.color.white_transparent), getResources().getColor(R.color.black));
                changeCardViewBackground(fullBodyCard, findViewById(R.id.fullBodyTextView), getResources().getColor(R.color.grey_transparent), getResources().getColor(R.color.white));
                changeCardViewBackground(upperBodyCard, findViewById(R.id.upperBodyTextView), getResources().getColor(R.color.grey_transparent), getResources().getColor(R.color.white));
                changeCardViewBackground(handsCard, findViewById(R.id.handsTextView), getResources().getColor(R.color.grey_transparent), getResources().getColor(R.color.white));

            }
        });

        handsCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView muscleGroup = view.findViewById(R.id.handsTextView);
                String muscleGroupAsAString = muscleGroup.getText().toString();

                modifyExerciseList(muscleGroupAsAString);

                changeCardViewBackground(handsCard, findViewById(R.id.handsTextView), getResources().getColor(R.color.white_transparent), getResources().getColor(R.color.black));
                changeCardViewBackground(fullBodyCard, findViewById(R.id.fullBodyTextView), getResources().getColor(R.color.grey_transparent), getResources().getColor(R.color.white));
                changeCardViewBackground(upperBodyCard, findViewById(R.id.upperBodyTextView), getResources().getColor(R.color.grey_transparent), getResources().getColor(R.color.white));
                changeCardViewBackground(lowerBodyCard, findViewById(R.id.lowerBodyTextView), getResources().getColor(R.color.grey_transparent), getResources().getColor(R.color.white));
            }
        });
    }

    private void modifyExerciseList(String muscleGroup)
    {
        //shuffle the exercises list for testing purposes...
        ArrayList<Exercise> availableExercises = exerciseList.getAvailableExercises();
        Collections.shuffle(availableExercises);
        exerciseListAdapter.setExercises(availableExercises);
    }

    private void changeCardViewBackground(CardView cardView, TextView textView, int backgroundColor, int textColor)
    {
        cardView.setCardBackgroundColor(backgroundColor);
        textView.setTextColor(textColor);
    }
}