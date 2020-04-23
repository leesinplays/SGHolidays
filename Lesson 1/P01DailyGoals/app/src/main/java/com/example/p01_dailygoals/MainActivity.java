package com.example.p01_dailygoals;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get the button and set the OnClickListener
        Button btnDone = (Button) findViewById(R.id.OK);
        btnDone.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0) {

                TextView tv1 = (TextView) findViewById(R.id.tvMaterials);

                // Get the RadioGroup object
                RadioGroup rg1 = (RadioGroup) findViewById(R.id.radioGroup1);
                // Get the Id of the selected radio button in the RadioGroup
                int selectedButtonId1 = rg1.getCheckedRadioButtonId();
                // Get the radio button object from the Id we had gotten above
                RadioButton rb1 = (RadioButton) findViewById(selectedButtonId1);

                RadioGroup rg2 = (RadioGroup) findViewById(R.id.radioGroup2);
                int selectedButtonId2 = rg2.getCheckedRadioButtonId();
                RadioButton rb2 = (RadioButton) findViewById(selectedButtonId2);

                RadioGroup rg3 = (RadioGroup) findViewById(R.id.radioGroup3);
                int selectedButtonId3 = rg3.getCheckedRadioButtonId();
                RadioButton rb3 = (RadioButton) findViewById(selectedButtonId3);

                // Get the EditText that user keys in reflection
                EditText etReflection = (EditText) findViewById(R.id.reflection);
                // Put the info into an array
                String[] info = {rb1.getText().toString(),
                        rb2.getText().toString(),
                        rb3.getText().toString(),
                        etReflection.getText().toString()};
                // Create an intent to start another activity called
                //  DemoActivities (which we would create later)
                Intent i = new Intent(MainActivity.this,
                        Summary.class);
                // Pass the String array holding the name & age to new activity
                i.putExtra("info", info);
                // Start the new activity
                startActivity(i);
            }
        });
    }
}
