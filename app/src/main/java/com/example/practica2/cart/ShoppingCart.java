package com.example.practica2.cart;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.practica2.R;

public class ShoppingCart extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_cart);
        ShoppingCartFragment shoppingCartFragment = (ShoppingCartFragment) getSupportFragmentManager().findFragmentById(R.id.shoppingCartFragment);
        assert shoppingCartFragment != null;
    }

}
