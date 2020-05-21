package com.example.song;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class ThirdActivity extends AppCompatActivity {

    EditText etId, etTitle, etSingers, etYear;
    RadioButton radio1, radio2, radio3, radio4, radio5;
    Song song;
    Button btnUpdate, btnDelete, btnCancel;
    RadioGroup rgRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        etId = (EditText) findViewById(R.id.editTextId);
        etTitle = (EditText) findViewById(R.id.editTextTitle);
        etSingers = (EditText) findViewById(R.id.editTextSingers);
        etYear = (EditText) findViewById(R.id.editTextYear);
        radio1 = (RadioButton) findViewById(R.id.rating1);
        radio2 = (RadioButton) findViewById(R.id.rating2);
        radio3 = (RadioButton) findViewById(R.id.rating3);
        radio4 = (RadioButton) findViewById(R.id.rating4);
        radio5 = (RadioButton) findViewById(R.id.rating5);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnCancel = (Button) findViewById(R.id.btnCancel);
        rgRating = (RadioGroup) findViewById(R.id.rgRating);

        Intent i = getIntent();
        song = (Song) i.getSerializableExtra("song");
        etId.setText(String.valueOf(song.getId()));
        etTitle.setText(song.getTitle());
        etSingers.setText(song.getSingers());
        etYear.setText(song.getYear());
        int stars = song.getStars();
        if (stars == 1) {
            radio1.setChecked(true);
        } else if (stars == 2) {
            radio2.setChecked(true);
        } else if (stars == 3) {
            radio3.setChecked(true);
        } else if (stars == 4) {
            radio4.setChecked(true);
        } else if (stars == 5) {
            radio5.setChecked(true);
        }

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                song.setTitle(etTitle.getText().toString());
                song.setSingers(etSingers.getText().toString());
                song.setYear(Integer.parseInt(etYear.getText().toString()));
                song.setStars(getStars());
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ThirdActivity.this);
                dbh.deleteSong(song.getId());
                dbh.close();
                Intent returnIntent = new Intent();
                setResult(RESULT_OK, returnIntent);
                finish();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });
    }

    private int getStars() {
        int stars = 1;
        switch (rgRating.getCheckedRadioButtonId()) {
            case R.id.rating1:
                stars = 1;
                break;
            case R.id.rating2:
                stars = 2;
                break;
            case R.id.rating3:
                stars = 3;
                break;
            case R.id.rating4:
                stars = 4;
                break;
            case R.id.rating5:
                stars = 5;
                break;
        }
        return stars;
    }
}
