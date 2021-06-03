package com.example.practica2.contact;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import com.example.practica2.MainActivity;
import com.example.practica2.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class map extends FragmentActivity implements OnMapReadyCallback {
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        TextView textLogo = findViewById(R.id.cubeBusterLogoMap);
        textLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeClick(v);
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Add a marker in Sydney and move the camera
        LatLng cubeBustersHq = new LatLng(40.538430, -3.893596);
        mMap.addMarker(new MarkerOptions().position(cubeBustersHq).title("Cubebusters Headquarters"));
        LatLng cubeBustersMobile = new LatLng(20, -40);
        mMap.addMarker(new MarkerOptions().position(cubeBustersMobile).title("Cubebusters Mobile"));
        LatLng cubeBustersAsia = new LatLng(69, 69);
        mMap.addMarker(new MarkerOptions().position(cubeBustersAsia).title("Cubebusters Asia"));

        LatLng[] cubeBustersSmile = new LatLng[11];
        cubeBustersSmile[0] = new LatLng(-78.2, 62);
        cubeBustersSmile[1] = new LatLng(-79.4, 67);
        cubeBustersSmile[2] = new LatLng(-80.6, 72);
        cubeBustersSmile[3] = new LatLng(-81, 77);
        cubeBustersSmile[4] = new LatLng(-81, 82);
        cubeBustersSmile[5] = new LatLng(-81, 87);
        cubeBustersSmile[6] = new LatLng(-80.6, 92);
        cubeBustersSmile[7] = new LatLng(-79.4, 97);
        cubeBustersSmile[8] = new LatLng(-78.2, 102);
        cubeBustersSmile[9] = new LatLng(-75, 72);
        cubeBustersSmile[10] = new LatLng(-75, 92);

        for(int i = 0; i < 11; i++){
            mMap.addMarker(new MarkerOptions().position(cubeBustersSmile[i]));
        }

        mMap.moveCamera(CameraUpdateFactory.newLatLng(cubeBustersHq));
    }


    private void homeClick(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}