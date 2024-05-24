/*
Author: Thanos Moschou
Description: This is a simple fitness app.
 */

package com.example.ufit.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ufit.R;
import com.example.ufit.adapters.ExerciseListAdapter;
import com.example.ufit.adapters.ExerciseTypesAdapter;
import com.example.ufit.model.Exercise;
import com.example.ufit.model.ExerciseList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class CentralScreenActivity extends AppCompatActivity
{
    private RecyclerView exercisesRecyclerView;
    private RecyclerView exerciseTypesRecyclerView;
    private ExerciseList exerciseList;
    private ExerciseListAdapter exerciseListAdapter;
    private ImageView notificationBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_central_screen);

        List<String> exercTypes = List.of(getResources().getStringArray(R.array.exerciseTypes));
        ExerciseTypesAdapter exerciseTypesAdapter = new ExerciseTypesAdapter(getApplicationContext());
        exerciseTypesAdapter.setExerciseTypesList(exercTypes);

        exerciseTypesRecyclerView = findViewById(R.id.exerciseTypesRecyclerView);
        exerciseTypesRecyclerView.setAdapter(exerciseTypesAdapter);
        exerciseTypesRecyclerView.setLayoutManager(new LinearLayoutManager(CentralScreenActivity.this, RecyclerView.HORIZONTAL, false));

        exerciseList = new ExerciseList(getAssets());

        exerciseListAdapter = new ExerciseListAdapter(CentralScreenActivity.this);
        exerciseListAdapter.setExercises(exerciseList.getAvailableExercises());

        exercisesRecyclerView = findViewById(R.id.exercisesRecyclerView);
        exercisesRecyclerView.setAdapter(exerciseListAdapter);
        exercisesRecyclerView.setLayoutManager(new LinearLayoutManager(CentralScreenActivity.this));

        notificationBtn = findViewById(R.id.notificationBtn);
        notificationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageView notificationBtnBackground = findViewById(R.id.notificationBtnBackground);
                notificationBtnBackground.setBackground(getResources().getDrawable(R.drawable.circle_background_white_transparent));

                notificationBtn.setImageResource(R.drawable.baseline_notifications_none_24_black);
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