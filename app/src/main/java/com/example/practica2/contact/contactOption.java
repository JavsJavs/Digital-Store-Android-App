package com.example.practica2.contact;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.practica2.MainActivity;
import com.example.practica2.R;

public class contactOption extends AppCompatActivity {
    private TextView errorDisplay;
    private TextView textLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_option);
        this.errorDisplay = findViewById(R.id.radioErrorText);
        this.errorDisplay.setText("");
        Button addProductButton = findViewById(R.id.contactButton);
        addProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contactClick(v);
            }
        });

        this.textLogo = findViewById(R.id.cubeBusterLogoOption);
        textLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeClick(v);
            }
        });
    }

    @SuppressLint({"NonConstantResourceId", "SetTextI18n"})
    private void contactClick(View v){
        RadioGroup group = findViewById(R.id.contactOptionGroup);
        int id = group.getCheckedRadioButtonId();
        Intent intent;
        switch (id)
        {
            case R.id.businessRadio:
                this.errorDisplay.setText("");
                Log.e("Option","Request");
                intent = new Intent(this, request.class);
                startActivity(intent);
                break;
            case R.id.orderRadio:
                this.errorDisplay.setText("");
                Log.e("Option","Claim");
                intent = new Intent(this, orderClaim.class);
                startActivity(intent);
                break;
            default:
                Log.e("Error", "Error");
                this.errorDisplay.setText("Please mark one of these options");
                break;
        }
    }

    private void homeClick(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
