package com.example.practica2.pages;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.practica2.R;
import com.example.practica2.cart.ShoppingCart;
import com.example.practica2.database.FilmDataHelper;

public class FilmDetail extends AppCompatActivity {
    private int currentItems;
    private int id;
    private float price;
    Button addFilmButton;
    Button removeFilmButton;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("SetTextI18n")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_detail);
        this.id = Integer.parseInt(getIntent().getStringExtra("FilmId"));
        FilmDataHelper filmDbHelper = new FilmDataHelper(this);
        try {
            SQLiteDatabase db = filmDbHelper.getReadableDatabase();
            Cursor cursor = db.query("FILMS",
                    new String[]{"_id", "NAME", "DESCRIPTION", "PRICE", "PLATFORM", "IMAGE_ID", "IS_NEW", "BOUGHT", "OFFER"},
                    "_id = ?",
                    new String[]{Integer.toString(id)},
                    null, null, null);
            cursor.moveToFirst();
            ((TextView) findViewById(R.id.itemDetailTitle)).setText(cursor.getString(FilmDataHelper.filmTable.NAME));
            this.currentItems = cursor.getInt(FilmDataHelper.filmTable.BOUGHT);
            this.price = cursor.getFloat(FilmDataHelper.filmTable.PRICE);
            ((TextView) findViewById(R.id.itemDetailDescription)).setText(cursor.getString(FilmDataHelper.filmTable.DESC));
            ((TextView) findViewById(R.id.itemDetailPrice)).setText(cursor.getString(FilmDataHelper.filmTable.PRICE));
            if (cursor.getString(FilmDataHelper.filmTable.PLATFORM).equals("dvd"))
                ((TextView) findViewById(R.id.itemDetailPlatform)).setText("DvD");
            else
                ((TextView) findViewById(R.id.itemDetailPlatform)).setText("BlueRay");
            String imageId = cursor.getString(FilmDataHelper.filmTable.IMAGE_ID);
            int imageIdSourced = getResources().getIdentifier(imageId, "drawable", getPackageName());
            ImageView posterImage = findViewById(R.id.itemDetailmage);
            if (String.valueOf(imageIdSourced).equals("0")) {
                posterImage.setImageResource(getResources().getIdentifier("default_white", "drawable", getPackageName()));
            } else {
                posterImage.setImageResource(imageIdSourced);
            }
            int isNew = cursor.getInt(FilmDataHelper.filmTable.IS_NEW);
            ImageView newImage = findViewById(R.id.itemDetailNew);
            if (isNew == 0) {
                newImage.setImageResource(getResources().getIdentifier("watch_off", "drawable", getPackageName()));
            } else {
                newImage.setImageResource(getResources().getIdentifier("watch_on", "drawable", getPackageName()));
            }
            int isOffer = cursor.getInt(FilmDataHelper.filmTable.OFFER);
            ImageView offerImage = findViewById(R.id.itemDetailOffer);
            if (isOffer == 0) {
                offerImage.setImageResource(getResources().getIdentifier("offer_off", "drawable", getPackageName()));
            } else {
                offerImage.setImageResource(getResources().getIdentifier("offer_on", "drawable", getPackageName()));
            }
        } catch (Exception e) {
            Log.e("filmDetail", "catched");
        }
        this.addFilmButton = findViewById(R.id.itemDetailAdd);
        this.removeFilmButton = findViewById(R.id.itemDetailRemove);
        addFilmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeNumberFilm(1);
            }
        });
        removeFilmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeNumberFilm(-1);
            }
        });
        updateItemsBought();
        Button cartButton = findViewById(R.id.itemDetailGoToCart);
        cartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ShoppingCart.class);
                startActivity(intent);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void changeNumberFilm(int sign) {
        int nextItems;
        ContentValues filmValues = new ContentValues();
        switch (sign) {
            case 1:
                nextItems = currentItems + 1;
                break;
            case -1:
                if (this.currentItems > 0)
                    nextItems = currentItems - 1;
                else
                    nextItems = 0;
                break;
            default:
                nextItems = currentItems;
        }
        filmValues.put("BOUGHT", nextItems);
        SQLiteOpenHelper filmDbHelper = new FilmDataHelper(this);
        SQLiteDatabase db = filmDbHelper.getReadableDatabase();
        db.update("FILMS",
                filmValues,
                "_id = ?",
                new String[]{Integer.toString(this.id)});
        this.currentItems = nextItems;
        updateItemsBought();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void updateItemsBought() {
        if (this.currentItems == 0) {
            this.removeFilmButton.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.primary_super_dark)));
            this.removeFilmButton.setClickable(false);
        } else {
            this.removeFilmButton.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.secondary_light)));
            this.removeFilmButton.setClickable(true);
        }
        ((TextView) findViewById(R.id.itemDetailBought)).setText(String.valueOf(this.currentItems));
        updateTotalCost();
    }

    @SuppressLint("SetTextI18n")
    private void updateTotalCost() {
        TextView totalText = findViewById(R.id.totalText);
        TextView totalNumber = findViewById(R.id.totalNumber);
        TextView totalDollar = findViewById(R.id.totalDollar);
        if (this.currentItems > 0) {
            totalText.setText("Total");
            totalNumber.setText(String.valueOf(this.price * this.currentItems));
            totalDollar.setText("$");
        } else {
            totalText.setText("");
            totalNumber.setText("");
            totalDollar.setText("");
        }
    }
}