package sg.edu.rp.lp4quiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class ShowMap extends AppCompatActivity {

    private GoogleMap map;
    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_map);

        btnBack = (Button) findViewById(R.id.btnBack);

        Intent i = getIntent();
        final Float lat = Float.valueOf(i.getStringExtra("lat"));
        final Float lng = Float.valueOf(i.getStringExtra("lng"));

        FragmentManager fm = getSupportFragmentManager();
        SupportMapFragment mapFragment=(SupportMapFragment)
                fm.findFragmentById(R.id.map);

        mapFragment.getMapAsync(new OnMapReadyCallback(){
            @Override
            public void onMapReady(GoogleMap googleMap){
                map = googleMap;

                UiSettings ui = map.getUiSettings();

                ui.setCompassEnabled(true);
                ui.setZoomControlsEnabled(true);

                LatLng poi_whereAmI = new LatLng(lat,lng);
                Marker location = map.addMarker(new
                        MarkerOptions()
                        .position(poi_whereAmI)
                        .title("Saved Coordinates")
                        .snippet("18016599 was here")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));

                map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_whereAmI,
                        12));
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
