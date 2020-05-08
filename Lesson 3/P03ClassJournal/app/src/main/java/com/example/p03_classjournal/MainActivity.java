package com.example.p03_classjournal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lvModule;
    ArrayList<ModuleCode> moduleCodes;
    ArrayAdapter aaType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvModule = (ListView) this.findViewById(R.id.lvModule);

        moduleCodes = new ArrayList<ModuleCode>();
        moduleCodes.add(new ModuleCode("C302", "Web Services"));
        moduleCodes.add(new ModuleCode("C347", "Android Programming II"));

        aaType = new ModuleAdapter(this, R.layout.modulerow, moduleCodes);
        lvModule.setAdapter(aaType);


        lvModule.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ModuleCode selectedCode = moduleCodes.get(position);

                Intent intent = new Intent(MainActivity.this, InfoMain.class);
                intent.putExtra(Intent.EXTRA_TEXT, selectedCode.getCode());
                startActivity(intent);
            }
        });

    }

}
