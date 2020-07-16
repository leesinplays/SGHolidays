package sg.edu.rp.p09_gettingmylocations;

import android.Manifest;
import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.core.content.PermissionChecker;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

import java.io.File;
import java.io.FileWriter;

public class MyService extends Service {

    boolean started;
    FusedLocationProviderClient client;
    LocationRequest mLocationRequest;
    LocationCallback mLocationCallback;

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        Log.d("Service", "Service created");
        super.onCreate();
        client = LocationServices.getFusedLocationProviderClient(this);

        createLocationCallBack();

        String folderLocation = Environment.getExternalStorageDirectory().getAbsolutePath() +"/P09";
        File folder = new File(folderLocation);
        if (!folder.exists()) {
            boolean result = folder.mkdir();
            if (!result) {
                Toast.makeText(MyService.this, "Folder can't be created in External memory," +
                        " Service Exiting", Toast.LENGTH_SHORT).show();
                stopSelf();
            }
        }

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (!started) {
            started = true;
            Log.d("Service", "Service started");
            if (checkPermission()) {
                mLocationRequest = new LocationRequest();
                mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
                mLocationRequest.setInterval(10000);
                mLocationRequest.setFastestInterval(5000);
                mLocationRequest.setSmallestDisplacement(100);

                client.requestLocationUpdates(mLocationRequest, mLocationCallback, null);
            } else {
                stopSelf();
            }
        } else {
            Log.d("Service", "Service is still running");
        }
        return Service.START_STICKY;
    }

    @Override
    public void onDestroy() {
        client.removeLocationUpdates(mLocationCallback);
        Log.d("Service", "Service exited");
        super.onDestroy();
    }

    private void createLocationCallBack() {
        mLocationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult != null) {
                    Location data = locationResult.getLastLocation();
                    double lat = data.getLatitude();
                    double lng = data.getLongitude();
                    String latlng = lat + "," + lng;
                    Log.d("Service - Loc Changed", latlng);

                    String folderLocation = Environment.getExternalStorageDirectory().getAbsolutePath() +"/P09";
                    File targetFile = new File(folderLocation, "data.txt");

                    try {
                        FileWriter writer = new FileWriter(targetFile, true);
                        writer.write(latlng+"\n");
                        writer.flush();
                        writer.close();

                    } catch (Exception e) {
                        Toast.makeText(MyService.this, "Failed to Write!",
                                Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                }
            }
        };
    }

    private boolean checkPermission() {
        int permissionCheck_Coarse = ContextCompat.checkSelfPermission(
                MyService.this, Manifest.permission.ACCESS_COARSE_LOCATION);
        int permissionCheck_Fine = ContextCompat.checkSelfPermission(
                MyService.this, Manifest.permission.ACCESS_FINE_LOCATION);
        int permissionCheck_write = ContextCompat.checkSelfPermission(
                MyService.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permissionCheck_Coarse == PermissionChecker.PERMISSION_GRANTED
                && permissionCheck_Fine == PermissionChecker.PERMISSION_GRANTED
                && permissionCheck_write == PermissionChecker.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }
}
