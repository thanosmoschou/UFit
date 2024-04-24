/*
Author: Thanos Moschou
Description: This is a simple fitness app.
 */

package com.example.ufit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;


public class CentralScreenActivity extends AppCompatActivity
{
    private RecyclerView exercisesView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_central_screen);


        //TESTING THE ITEMS LAYOUT...
        ExerciseList exerciseList = new ExerciseList(getAssets());

        //I needed to create a custom adapter.
        //The RequestItem class matches exactly to the Request-Item from the database model
        ExerciseListAdapter exerciseAdapter = new ExerciseListAdapter(CentralScreenActivity.this);
        exerciseAdapter.setExercises(exerciseList.getAvailableExercises());

        exercisesView = findViewById(R.id.exercisesView);
        exercisesView.setAdapter(exerciseAdapter);
        exercisesView.setLayoutManager(new LinearLayoutManager(CentralScreenActivity.this));


    }
}