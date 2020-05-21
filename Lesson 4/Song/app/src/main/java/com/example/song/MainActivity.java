package com.example.song;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etTitle, etSingers, etYear;
    Button btnInsert, btnShow;
    RadioGroup rgStars;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTitle = (EditText) findViewById(R.id.etTitle);
        etSingers = (EditText) findViewById(R.id.etSingers);
        etYear = (EditText) findViewById(R.id.etYear);
        btnInsert = (Button) findViewById(R.id.btnInsert);
        btnShow = (Button) findViewById(R.id.btnShow);
        rgStars = (RadioGroup) findViewById(R.id.rgStars);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String note = etTitle.getText().toString().trim();
                if (note.length() == 0)
                    return;

                DBHelper dbh = new DBHelper(MainActivity.this);
                if (dbh.isExistingSong(etTitle.getText().toString())) {
                    Toast.makeText(MainActivity.this, "Same song already exists", Toast.LENGTH_LONG).show();
                } else {
                    String title = etTitle.getText().toString();
                    String singers = etSingers.getText().toString();
                    int year = Integer.parseInt(etYear.getText().toString());
                    int stars = getStars();
                    dbh.insertSong(title, singers, year, stars);
                    Toast.makeText(MainActivity.this, "Inserted", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent i = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(i);
            }
        });
    }

    private int getStars() {
        int stars = 1;
        switch (rgStars.getCheckedRadioButtonId()) {
            case R.id.stars1:
                stars = 1;
                break;
            case R.id.stars2:
                stars = 2;
                break;
            case R.id.stars3:
                stars = 3;
                break;
            case R.id.stars4:
                stars = 4;
                break;
            case R.id.stars5:
                stars = 5;
                break;
        }
        return stars;
    }
}
