package com.example.ufit.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ufit.model.Exercise;
import com.example.ufit.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;

public class ExerciseListAdapter extends RecyclerView.Adapter<ExerciseListAdapter.ViewHolder>
{

    private List<Exercise> exercises;
    private Context context;

    public ExerciseListAdapter(Context aContext)
    {
        this.context = aContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        //Here we instantiate the view holder class. First we need to create a View object
        //The ViewGroup is the parent of all layout files.
        //We pass parent because we want to attach this view object to its parent.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_exercise_layout, parent, false);

        //Now that I have the view object I can create an instance of my view holder
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    //The most important method of this class
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position)
    {
        //here we use the properties of our view holder class
        holder.exerciseNameTxt.setText(exercises.get(position).getName());
        holder.setsTxt.setText(Integer.toString(exercises.get(position).getSets()));
        holder.exerciseIcon.setImageResource(R.drawable.mindset_is_everything);

        //Let's create a listener for each element. I will implement a listener for the relative layout and not only for the text view
        //that is inside my layout file
        holder.singleExerciseCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Exercise selected = null;
                String exerciseName = holder.exerciseNameTxt.getText().toString();

                for(Exercise ex : exercises)
                    if(ex.hasName(exerciseName))
                    {
                        selected = ex;
                        break;
                    }

                if(selected != null)
                {
                    BottomSheetDialog detailsDialog = new BottomSheetDialog(context);
                    detailsDialog.setContentView(R.layout.exercise_details_layout);

                    TextView exerciseDescription = detailsDialog.findViewById(R.id.exerciseDescription);
                    exerciseDescription.setText(selected.getDescription());

                    detailsDialog.show();
                }
            }
        });

    }

    @Override
    public int getItemCount()
    {
        return exercises.size();
    }

    public void setExercises(List<Exercise> exercises)
    {
        this.exercises = exercises;
        notifyDataSetChanged(); //notify the adapter that the data have changed
    }

    //This inner class is going to hold the view items for every item in our recycler view.
    //I can instantiate all the view items that are inside my names list item layout file inside the constructor of this inner class.
    //In order to have access to elements inside the view objects you need to declare to the inner class.
    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private TextView exerciseNameTxt; //If I have more elements inside my layout file I can add them in the same way I did here.
        private ImageView exerciseIcon;
        private TextView setsTxt;
        private CardView singleExerciseCard;


        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            exerciseNameTxt = itemView.findViewById(R.id.exerciseNameTxt);
            exerciseIcon = itemView.findViewById(R.id.exerciseIcon);
            setsTxt = itemView.findViewById(R.id.setsTxt);
            singleExerciseCard = itemView.findViewById(R.id.singleExerciseCard);
        }
    }
}
