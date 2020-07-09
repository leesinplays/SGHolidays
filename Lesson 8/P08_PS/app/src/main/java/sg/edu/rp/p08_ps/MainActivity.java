package sg.edu.rp.p08_ps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.PermissionChecker;
import androidx.fragment.app.FragmentManager;

import android.Manifest;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity {

    Button btn1, btn2, btn3;
    TextView tv;
    private GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.tv);
        tv.setText("ABC Pte Ltd\n\nWe now have 3 branches. Look below for the address and info");

        FragmentManager fm = getSupportFragmentManager();
        SupportMapFragment mapFragment = (SupportMapFragment)
                fm.findFragmentById(R.id.map);

        mapFragment.getMapAsync(new OnMapReadyCallback(){
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map = googleMap;

                UiSettings ui = map.getUiSettings();

                ui.setCompassEnabled(true);
                ui.setZoomControlsEnabled(true);
                ui.setMapToolbarEnabled(true);

                LatLng poi_north = new LatLng(1.461708, 103.813500);
                LatLng poi_central = new LatLng(1.300542, 103.841226);
                LatLng poi_east = new LatLng(1.350057,103.934452);
                LatLng singapore = new LatLng(1.3521,103.8198);
                Marker north = map.addMarker(new
                        MarkerOptions()
                        .position(poi_north)
                        .title("North - HQ")
                        .snippet("Block 333, Admiralty Ave 3, 765654\n" +
                                "Operating hours: 10am-5pm\n" +
                                "Tel:65433456\n")
                        .icon(BitmapDescriptorFactory.fromResource(android.R.drawable.star_big_on)));

                Marker central = map.addMarker(new
                        MarkerOptions()
                        .position(poi_central)
                        .title("Central")
                        .snippet("Block 3A, Orchard Ave 3, 134542 \n" +
                                "Operating hours: 11am-8pm\n" +
                                "Tel:67788652\n")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

                Marker east = map.addMarker(new
                        MarkerOptions()
                        .position(poi_east)
                        .title("East")
                        .snippet("Block 555, Tampines Ave 3, 287788 \n" +
                                "Operating hours: 9am-5pm\n" +
                                "Tel:66776677\n")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));

                map.moveCamera(CameraUpdateFactory.newLatLngZoom(singapore,
                        15));

                map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {
                        String name = marker.getTitle();
                        if (name.equals("North - HQ")) {
                            Toast.makeText(MainActivity.this,
                                    "North - HQ", Toast.LENGTH_SHORT).show();
                            return true;
                        } else if (name.equals("Central")) {
                            Toast.makeText(MainActivity.this,
                                    "Central", Toast.LENGTH_SHORT).show();
                            return true;
                        } else if (name.equals("East")) {
                            Toast.makeText(MainActivity.this,
                                    "East", Toast.LENGTH_SHORT).show();
                            return true;
                        } else {
                            return false;
                        }
                    }
                });
            }
        });

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (map != null){
                    LatLng poi_north = new LatLng(1.461708, 103.813500);
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_north,
                            15));
                }
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (map != null){
                    LatLng poi_central = new LatLng(1.300542, 103.841226);
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_central,
                            15));
                }
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (map != null){
                    LatLng poi_east = new LatLng(1.350057,103.934452);
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_east,
                            15));
                }
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        int permissionCheck = ContextCompat.checkSelfPermission(MainActivity.this,
                android.Manifest.permission.ACCESS_FINE_LOCATION);

        if (permissionCheck == PermissionChecker.PERMISSION_GRANTED) {
            map.setMyLocationEnabled(true);
        } else {
            Log.e("GMap - Permission", "GPS access has not been granted");
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 0);
        }

    }
}
