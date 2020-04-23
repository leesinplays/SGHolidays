package com.example.p01_dailygoals;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Summary extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        // Get the intent so as to get the "things" inside the intent
        Intent i = getIntent();
        // Get the String array named "info" we passed in
        String[] info = i.getStringArrayExtra("info");
        // Get the TextView object
        TextView tv1 = findViewById(R.id.summary);
        // Display the info on the TextView
        tv1.setText("Read up on materials before class: " + info[0] +
                "\nArrive on time so as not to miss important part of the lesson: " + info[1] +
                "\nAttempt the problem myself: " + info[2] +
                "\nReflection: " + info[3]);
    }
}
