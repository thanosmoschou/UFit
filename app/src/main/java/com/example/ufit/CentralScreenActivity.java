/*
Author: Thanos Moschou
Description: This is a simple fitness app.
 */

package com.example.ufit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class CentralScreenActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_central_screen);

        //TESTING THE ITEMS LAYOUT...
        List<Exercise> exercises = new ArrayList<>();
        exercises.add(new Exercise("Push Ups", 3));
        exercises.add(new Exercise("Abs", 3));
        exercises.add(new Exercise("Squats", 3));
        exercises.add(new Exercise("Bench Press", 3));
        exercises.add(new Exercise("Dumbbell Rows", 3));
        exercises.add(new Exercise("Donkey Kicks", 3));
        exercises.add(new Exercise("Calf Raises", 3));

        //I needed to create a custom adapter.
        //The RequestItem class matches exactly to the Request-Item from the database model
        ExerciseListAdapter exerciseAdapter = new ExerciseListAdapter(getApplicationContext(), exercises);

        ListView exercisesList = findViewById(R.id.exercisesList);
        exercisesList.setAdapter(exerciseAdapter);
    }
}