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
    private boolean checkError(){
        boolean error = false;
        if(String.valueOf(this.nameText.getText()).equals("")){
            nameText.setHint("This field is required");
            nameText.setHintTextColor(getResources().getColor(R.color.secondary_dark));
            error = true;
        }
        if(String.valueOf(subjectText.getText()).equals("")){
            subjectText.setHint("This field is required");
            subjectText.setHintTextColor(getResources().getColor(R.color.secondary_dark));
            error = true;
        }
        if("".equals(String.valueOf(requestText.getText()))){
            requestText.setHint("This field is required");
            requestText.setHintTextColor(getResources().getColor(R.color.secondary_dark));
            error = true;
        }
        return error;
    }

    private void resetText(){
        this.nameText.setHintTextColor(getResources().getColor(R.color.primary_light));
        this.subjectText.setHintTextColor(getResources().getColor(R.color.primary_light));
        this.requestText.setHintTextColor(getResources().getColor(R.color.primary_light));
    }

    private void sendMail(View v){
        resetText();
        if(!checkError()) {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            //"tech_support@cubebusters.net"
            intent.putExtra(Intent.EXTRA_EMAIL, new String[] {"tech_support@cubebusters.net"});
            intent.putExtra(Intent.EXTRA_SUBJECT, String.valueOf(this.subjectText.getText()));
            intent.putExtra(Intent.EXTRA_TEXT, String.valueOf(this.requestText.getText()) + "\n\n" + String.valueOf(this.nameText.getText()));
            startActivity(intent);
        }else{
            Log.e("Contact Request", "Invalid form");
        }
    }
}