package com.example.ufit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class ExerciseListAdapter extends ArrayAdapter<Exercise>
{

    public ExerciseListAdapter(@NonNull Context context, List<Exercise> exercises)
    {
        super(context, 0, exercises);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        Exercise exercise = getItem(position);

        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_exercise_layout, parent, false);

        TextView exerciseNameTxt = convertView.findViewById(R.id.exerciseNameTxt);
        TextView setsTxt = convertView.findViewById(R.id.setsTxt);

        exerciseNameTxt.setText(exercise.getName());
        setsTxt.setText(Integer.toString(exercise.getSets()));

        return convertView;
    }
}
