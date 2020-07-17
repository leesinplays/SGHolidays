package sg.edu.rp.lp4quiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.PermissionChecker;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class MainActivity extends AppCompatActivity {

    String folderLocation;
    Button btnSave, btnShowMap;
    EditText etLat, etLng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSave = (Button) findViewById(R.id.btnSave);
        btnShowMap = (Button) findViewById(R.id.btnShowMap);
        etLat = (EditText) findViewById(R.id.etLat);
        etLng = (EditText) findViewById(R.id.etLng);

        folderLocation = Environment.getExternalStorageDirectory().getAbsolutePath() +
                "/LP4QUIZ";

        File folder = new File(folderLocation);
        if (!folder.exists()){
            boolean result = folder.mkdir();
            if (result){
                Log.d("File Read/Write", "Folder created");
            }
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String lat = etLat.getText().toString();
                String lng = etLng.getText().toString();
                if (checkPermission()) {
                    if (!lat.equals("") && !lng.equals("")) {
                        String details = lat + "," + lng;
                        File targetFile = new File(folderLocation, "details.txt");

                        if (targetFile.exists())
                            targetFile.delete();
                        try {
                            FileWriter writer = new FileWriter(targetFile, true);
                            writer.write(details + "\n");
                            writer.flush();
                            writer.close();
                            Toast.makeText(MainActivity.this,"Location Saved~",Toast.LENGTH_SHORT).show();

                        } catch (Exception e) {
                            Toast.makeText(MainActivity.this, "Failed to Write!",
                                    Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "Insert latitude or longitude!",
                                Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Permission not granted!",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnShowMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkPermission()) {
                    File targetFile = new File(folderLocation, "details.txt");

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
                            br.close();
                            reader.close();
                            String[] array = data.split(",");
                            Intent i = new Intent(MainActivity.this, ShowMap.class);
                            i.putExtra("lat",array[0]);
                            i.putExtra("lng",array[1]);
                            startActivity(i);
                        } catch (Exception e) {
                            Toast.makeText(MainActivity.this, "Failed to Read!",
                                    Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }
                        Toast.makeText(MainActivity.this, data, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Location file doesn't exist", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private boolean checkPermission() {
        int permissionCheck_Coarse = ContextCompat.checkSelfPermission(
                MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION);
        int permissionCheck_Fine = ContextCompat.checkSelfPermission(
                MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION);
        int permissionCheck_write = ContextCompat.checkSelfPermission(
                MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int permissionCheck_read = ContextCompat.checkSelfPermission(
                MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE);

        if (permissionCheck_Coarse == PermissionChecker.PERMISSION_GRANTED
                && permissionCheck_Fine == PermissionChecker.PERMISSION_GRANTED
                && permissionCheck_write == PermissionChecker.PERMISSION_GRANTED
                && permissionCheck_read == PermissionChecker.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }
}
