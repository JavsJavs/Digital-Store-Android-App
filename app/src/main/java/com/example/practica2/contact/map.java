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
        LatLng cubebustersHq = new LatLng(40.538430, -3.893596);
        mMap.addMarker(new MarkerOptions().position(cubebustersHq).title("Cubebusters Headquarters"));
        LatLng cubebustersMobile = new LatLng(20, -40);
        mMap.addMarker(new MarkerOptions().position(cubebustersMobile).title("Cubebusters Mobile"));
        LatLng cubebustersAsia = new LatLng(69, 69);
        mMap.addMarker(new MarkerOptions().position(cubebustersAsia).title("Cubebusters Asia"));


        LatLng cubebustersREye = new LatLng(-75, 72);
        mMap.addMarker(new MarkerOptions().position(cubebustersREye));
        LatLng cubebustersLEye = new LatLng(-75, 92);
        mMap.addMarker(new MarkerOptions().position(cubebustersLEye));

        LatLng cubebustersSmile1 = new LatLng(-78.2, 62);
        mMap.addMarker(new MarkerOptions().position(cubebustersSmile1));
        LatLng cubebustersSmile2 = new LatLng(-79.4, 67);
        mMap.addMarker(new MarkerOptions().position(cubebustersSmile2));
        LatLng cubebustersSmile3 = new LatLng(-80.6, 72);
        mMap.addMarker(new MarkerOptions().position(cubebustersSmile3));
        LatLng cubebustersSmile4 = new LatLng(-81, 77);
        mMap.addMarker(new MarkerOptions().position(cubebustersSmile4));
        LatLng cubebustersSmile5 = new LatLng(-81, 82);
        mMap.addMarker(new MarkerOptions().position(cubebustersSmile5));
        LatLng cubebustersSmile6 = new LatLng(-81, 87);
        mMap.addMarker(new MarkerOptions().position(cubebustersSmile6));
        LatLng cubebustersSmile7 = new LatLng(-80.6, 92);
        mMap.addMarker(new MarkerOptions().position(cubebustersSmile7));
        LatLng cubebustersSmile8 = new LatLng(-79.4, 97);
        mMap.addMarker(new MarkerOptions().position(cubebustersSmile8));
        LatLng cubebustersSmile9 = new LatLng(-78.2, 102);
        mMap.addMarker(new MarkerOptions().position(cubebustersSmile9));




        mMap.moveCamera(CameraUpdateFactory.newLatLng(cubebustersHq));
    }


    private void homeClick(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}