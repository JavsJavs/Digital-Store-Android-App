package com.example.practica2.contact;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.practica2.MainActivity;
import com.example.practica2.R;

public class map extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_map);

        TextView textLogo = findViewById(R.id.cubeBusterLogoMap);
        textLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeClick(v);
            }
        });
    }

    private void homeClick(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}