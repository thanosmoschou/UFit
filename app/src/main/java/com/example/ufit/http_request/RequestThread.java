package com.example.ufit.http_request;

import com.example.ufit.model.Exercise;

import java.util.List;

public class RequestThread extends Thread
{
    private HttpHandler httpHandler;
    private List<Exercise> availableExercises;
    private String exerciseType;

    public RequestThread(String exerciseType)
    {
        this.httpHandler = new HttpHandler();
        this.availableExercises = null;
        this.exerciseType = exerciseType.toLowerCase();
    }

    public void run()
    {
        availableExercises = httpHandler.getExercisesByType(exerciseType);
    }

    public List<Exercise> getAvailableExercises()
    {
        return this.availableExercises;
    }
}
