package com.example.ufit.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ufit.http_request.RequestThread;
import com.example.ufit.model.Exercise;
import com.example.ufit.R;

import java.util.List;

public class ExerciseTypesAdapter extends RecyclerView.Adapter<ExerciseTypesAdapter.ViewHolder>
{

    private List<String> exerciseTypesList;
    //I pass the exercise list and its adapter in order to modify the list and update the listview everytime
    //user selects a different exercise type
    private ExerciseListAdapter exerciseListAdapter;
    private List<Exercise> exerciseList;

    private Context context;
    private int selectedPosition = -1;

    public ExerciseTypesAdapter(Context aContext)
    {
        this.context = aContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.exercise_type_cardview_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position)
    {
        //here we use the properties of our view holder class
        holder.exerciseTypeName.setText(exerciseTypesList.get(position));

        if(position == selectedPosition)
        {
            holder.exerciseTypeCardView.setCardBackgroundColor(context.getResources().getColor(R.color.white));
            holder.exerciseTypeName.setTextColor(context.getResources().getColor(R.color.black));
        }
        else
        {
            holder.exerciseTypeCardView.setCardBackgroundColor(context.getResources().getColor(R.color.grey_transparent));
            holder.exerciseTypeName.setTextColor(context.getResources().getColor(R.color.white_transparent));
        }

        holder.exerciseTypeCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                String exerciseType = holder.exerciseTypeName.getText().toString();
                selectedPosition = position;

                RequestThread requestThread = new RequestThread(exerciseType);
                try
                {
                    requestThread.start();
                    requestThread.join();
                }
                catch (InterruptedException e)
                {
                    throw new RuntimeException(e);
                }

                exerciseList = requestThread.getAvailableExercises();

                //update the recommended workout exercise list
                exerciseListAdapter.setExercises(exerciseList);
                exerciseListAdapter.notifyDataSetChanged();

                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount()
    {
        return exerciseTypesList.size();
    }

    public void setExerciseTypesList(List<String> exerciseTypesList)
    {
        this.exerciseTypesList = exerciseTypesList;
        notifyDataSetChanged(); //notify the adapter that the data have changed
    }

    public void setExerciseList(List<Exercise> exerciseList)
    {
        this.exerciseList = exerciseList;
    }

    public void setExerciseListAdapter(ExerciseListAdapter exerciseListAdapter)
    {
        this.exerciseListAdapter = exerciseListAdapter;
    }

    //This inner class is going to hold the view items for every item in our recycler view.
    //I can instantiate all the view items that are inside my names list item layout file inside the constructor of this inner class.
    //In order to have access to elements inside the view objects you need to declare to the inner class.
    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private TextView exerciseTypeName;
        private CardView exerciseTypeCardView;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            exerciseTypeName = itemView.findViewById(R.id.exerciseTypeName);
            exerciseTypeCardView = itemView.findViewById(R.id.exerciseTypeCard);
        }
    }
}
