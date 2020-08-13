package com.myapplicationdev.android.lp5quiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.PermissionChecker;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button btnWrite, btnRead;
    TextView tvResults;
    String folderLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnWrite = findViewById(R.id.btnWrite);
        btnRead = findViewById(R.id.btnRead);
        tvResults = findViewById(R.id.tvResults);

        if (!checkPermission()) {
            btnRead.setEnabled(false);
            btnWrite.setEnabled(false);
        } else {
            btnRead.setEnabled(true);
            btnWrite.setEnabled(true);
        }

        folderLocation = Environment.getExternalStorageDirectory().getAbsolutePath() +
                "/lp5quiz";

        File folder = new File(folderLocation);
        if (!folder.exists()){
            boolean result = folder.mkdir();
            if (result){
                Log.d("File Read/Write", "Folder created");
            }
        }

        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File targetFile = new File(folderLocation, "data.txt");

                if (targetFile.exists()) {
                    String data = "";
                    try {
                        FileReader reader = new FileReader(targetFile);
                        BufferedReader br = new BufferedReader(reader);
                        String line = br.readLine();
                        while (line != null) {
                            data += line + "\n";
                            line = br.readLine();
                        }
                    } catch (Exception e) {
                        Toast.makeText(MainActivity.this, "Failed to Read!",
                                Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                    tvResults.setText(data);
                }
            }
        });

        btnWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File targetFile = new File(folderLocation, "data.txt");

                try {
                    FileWriter writer = new FileWriter(targetFile, true);
                    writer.write("My id is 18016599");
                    writer.flush();
                    writer.close();

                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Failed to Write!",
                            Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.action_search:
                Log.d("LP5Quiz","Search Action Item is clicked");
                break;
            case R.id.action_info:
                Intent intent = new Intent(MainActivity.this, AboutUsActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //to return true, if permissions have been granted, otherwise, false
    public boolean checkPermission() {
        int permissionCheck_write = ContextCompat.checkSelfPermission(
                MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int permissionCheck_read = ContextCompat.checkSelfPermission(
                MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE);

        if (permissionCheck_write == PermissionChecker.PERMISSION_GRANTED
                || permissionCheck_read == PermissionChecker.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 0: {

            }
        }
    }

}
