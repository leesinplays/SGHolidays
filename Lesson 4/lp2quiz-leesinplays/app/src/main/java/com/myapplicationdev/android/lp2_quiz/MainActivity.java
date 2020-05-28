package com.myapplicationdev.android.lp2_quiz;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    Button btnSubmit;
    EditText etData;
    ListView lv;
    ArrayList<ToDo> al;
    CustomAdapter ca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSubmit = findViewById(R.id.btnInsert);
        etData = findViewById(R.id.etData);
        lv = findViewById(R.id.lv);

        al = new ArrayList<>();
        ca = new CustomAdapter(this, R.layout.row, al);
        lv.setAdapter(ca);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = etData.getText().toString();
                String date = getDate();
                if (!data.isEmpty()) {
                    DBHelper dbhelper = new DBHelper(MainActivity.this);
                    dbhelper.insertToDo(data, date);
                }
                etData.setText("");
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(etData.getWindowToken(), 0);

            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ToDo n = al.get(position);
                Intent i = new Intent(MainActivity.this, ModifyActivity.class);
                i.putExtra("todo", n);
                startActivityForResult(i, 9);
            }
        });
    }

    protected String getDate() {
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
        String formattedDate = df.format(c);
        Log.i("Today's date : ", formattedDate);
        return formattedDate;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        DBHelper dbhelper = new DBHelper(this);
        switch (item.getItemId()) {
            case R.id.mnuRefresh:
                al.clear();
                al.addAll(dbhelper.getToDo());
                ca.notifyDataSetChanged();
                return true;
            case R.id.mnuRecent:
                al.clear();
                ;
                al.addAll(dbhelper.getToDoRecents());
                ca.notifyDataSetChanged();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == 9) {
            ca.notifyDataSetChanged();
        }

    }
}
