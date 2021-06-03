package com.example.practica2.contact;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.practica2.MainActivity;
import com.example.practica2.R;

public class request extends AppCompatActivity {
    private TextView errorText;
    private EditText nameText;
    private EditText subjectText;
    private EditText requestText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.e("Request","built");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_request);
        this.errorText = findViewById(R.id.requestErrorText);
        this.errorText.setText("");
        this.nameText = findViewById(R.id.editName);
        this.subjectText = findViewById(R.id.editEmail);
        this.requestText = findViewById(R.id.editRequest);
        Button addProductButton = findViewById(R.id.mailButton);
        addProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMail(v);
            }
        });

        TextView textLogo = findViewById(R.id.cubeBusterLogoRequest);
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

    @SuppressLint("SetTextI18n")
    private boolean checkForm(){
        return checkError(this.nameText) || checkError(this.subjectText) || checkError(this.requestText);
    }

    private boolean checkError(EditText editText){
        boolean error = false;
        if(String.valueOf(editText.getText()).equals("")){
            editText.setHint("This field is required");
            editText.setHintTextColor(getResources().getColor(R.color.secondary_dark));
            error = true;
        }
        return error;
    }

    private void resetText(){
        this.nameText.setHintTextColor(getResources().getColor(R.color.primary_super_dark));
        this.nameText.setHint("Write here…");
        this.subjectText.setHintTextColor(getResources().getColor(R.color.primary_super_dark));
        this.subjectText.setHint("Write here…");
        this.requestText.setHintTextColor(getResources().getColor(R.color.primary_super_dark));
        this.requestText.setHint("Write here…");
        this.errorText.setText("");
    }

    @SuppressLint("SetTextI18n")
    private void sendMail(View v){
        resetText();
        if(!checkForm()) {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            //"tech_support@cubebusters.net"
            intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"tech_support@cubebusters.net"});
            intent.putExtra(Intent.EXTRA_SUBJECT, String.valueOf(this.subjectText.getText()));
            intent.putExtra(Intent.EXTRA_TEXT, String.valueOf(this.requestText.getText()) + "\n\n" + String.valueOf(this.nameText.getText()));
            startActivity(intent);
        }else{
            this.errorText.setText("Please fill out every necessary field");
            Log.e("Contact Request", "Invalid form");
        }
    }
}