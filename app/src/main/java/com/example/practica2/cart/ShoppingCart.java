package com.example.practica2.cart;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.practica2.MainActivity;
import com.example.practica2.R;

public class ShoppingCart extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_cart);
        ShoppingCartFragment shoppingCartFragment = (ShoppingCartFragment) getSupportFragmentManager().findFragmentById(R.id.shoppingCartFragment);
        assert shoppingCartFragment != null;

        TextView textLogo = findViewById(R.id.cubeBusterLogoCart);
        textLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeClick(v);
            }
        });
    }

    private void homeClick(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);    }

}
