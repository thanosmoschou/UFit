/*
Author: Thanos Moschou
Description: This is a simple fitness app.
 */

package com.example.ufit.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.ufit.R;
import com.example.ufit.activities.CentralScreenActivity;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView introMsgTextView = findViewById(R.id.introMsg);
        String introText = getResources().getString(R.string.introMsg);

        // Create a SpannableString
        SpannableString spannableString = new SpannableString(introText);

        // Find the index of the word "stronger"
        int startIndex = introText.indexOf("stronger");
        int endIndex = startIndex + "stronger".length();

        // Set color to the word "stronger"
        spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.orange)), startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        // Set the SpannableString to your TextView
        introMsgTextView.setText(spannableString);

        Button getStartedBtn = findViewById(R.id.getStartedBtn);
        getStartedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CentralScreenActivity.class);
                startActivity(intent);
            }
        });

    }
}