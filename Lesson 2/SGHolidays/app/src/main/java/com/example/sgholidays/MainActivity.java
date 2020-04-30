package com.example.sgholidays;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lv;
    ArrayList<Type> type;
    ArrayAdapter aaType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = (ListView) this.findViewById(R.id.lvType);

        type = new ArrayList<Type>();
        type.add(new Type("Secular"));
        type.add(new Type("Ethnic & Religion"));

        aaType = new TypeAdapter(this, R.layout.typerow, type);
        lv.setAdapter(aaType);

//        ArrayAdapter adapter = new ArrayAdapter<String>(this,R.layout.row2,al);
//        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Type selectedType = type.get(position);

                Intent intent = new Intent(MainActivity.this, HolidayMain.class);
                intent.putExtra(Intent.EXTRA_TEXT, selectedType.getType());
                startActivity(intent);
            }
        });

    }
}
