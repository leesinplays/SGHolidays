package com.myapplicationdev.android.lp2_quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ModifyActivity extends AppCompatActivity {

    TextView tvID, tvDate;
    EditText etContent;
    Button btnUpdate, btnDelete;
    ToDo toDo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);

        //initialize the variables with UI here
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        tvID = (TextView) findViewById(R.id.tvID);
        tvDate = (TextView) findViewById(R.id.tvDate);
        etContent = (EditText) findViewById(R.id.etContent);

        Intent i = getIntent();
        toDo = (ToDo) i.getSerializableExtra("todo");

        tvID.setText("ID: " + toDo.getId());
        tvDate.setText("Date: " + toDo.getDate());
        etContent.setText(toDo.getData());

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etContent.getText().toString() == "") {
                    Toast.makeText(ModifyActivity.this, "Provide new data for update to current record",
                            Toast.LENGTH_SHORT).show();
                } else {
                    DBHelper dbh = new DBHelper(ModifyActivity.this);
                    toDo.setData(etContent.getText().toString());
                    dbh.updateToDo(toDo);
                    dbh.close();
                    Toast.makeText(ModifyActivity.this, "Record Updated",
                            Toast.LENGTH_SHORT).show();
                    Intent returnIntent = new Intent();
                    setResult(RESULT_OK, returnIntent);

                    finish();
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ModifyActivity.this);
                dbh.deleteToDo(toDo.getId());
                dbh.close();
                Toast.makeText(ModifyActivity.this, "Record Deleted",
                        Toast.LENGTH_SHORT).show();
                Intent returnIntent = new Intent();
                setResult(RESULT_OK, returnIntent);
                finish();
            }
        });


    }
}
