package com.example.practica2.cart;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.practica2.MainActivity;
import com.example.practica2.R;
import com.example.practica2.database.FilmDataHelper;

public class ShoppingCart extends AppCompatActivity {
    private Button buyButton;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
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

        this.buyButton = findViewById(R.id.buyButton);
        buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buyClick(v);
            }
        });
        updatePrice();
    }

    private void homeClick(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void buyClick(View v) {
        if(getPrice() > 0) {
            Intent intent = new Intent(this, ProcessCart.class);
            startActivity(intent);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void updatePrice() {
        TextView totalPrice = findViewById(R.id.totalNumber);
        float currentPrice = getPrice();
        totalPrice.setText(String.valueOf(currentPrice));
        if (currentPrice == 0.00) {
            this.buyButton.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.primary_super_dark)));
            this.buyButton.setClickable(false);
        } else {
            this.buyButton.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.secondary_light)));
            this.buyButton.setClickable(true);
        }
    }

    private float getPrice() {
        float price = 0;
        FilmDataHelper filmDbHelper = new FilmDataHelper(this);
        SQLiteDatabase db = filmDbHelper.getReadableDatabase();
        Cursor cursor = db.query("FILMS",
                new String[]{"_id", "NAME", "DESCRIPTION", "PRICE", "PLATFORM", "IMAGE_ID", "IS_NEW", "BOUGHT", "OFFER"},
                "bought > ?",
                new String[]{"0"},
                null, null, null);
        cursor.moveToFirst();
        for (int i = 0; i < cursor.getCount(); i++) {
            Log.e("Shopping cart", String.valueOf(price));
            price += cursor.getFloat(FilmDataHelper.filmTable.PRICE) * cursor.getInt(FilmDataHelper.filmTable.BOUGHT);
            cursor.moveToNext();
        }
        Log.e("Shopping cart", String.valueOf(price));
        return price;
    }
}