package com.example.practica2.contact;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.practica2.MainActivity;
import com.example.practica2.R;

public class help extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_help);

        TextView textLogo = findViewById(R.id.cubeBusterLogoHelp);
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
