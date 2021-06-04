package com.example.practica2.pages;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.practica2.R;
import com.example.practica2.database.FilmDataHelper;

public class FilmDetail extends AppCompatActivity {
    private int currentItems;
    private int id;
    private SQLiteDatabase db;
    private FilmDataHelper filmDbHelper;

    @SuppressLint("SetTextI18n")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_detail);
        this.id = Integer.parseInt(getIntent().getStringExtra("FilmId"));
        this.filmDbHelper = new FilmDataHelper(this);
        try {
            this.db = this.filmDbHelper.getReadableDatabase();
            Cursor cursor = this.db.query("FILMS",
                    new String[]{"_id", "NAME", "DESCRIPTION", "PRICE", "PLATFORM", "IMAGE_ID", "IS_NEW", "BOUGHT", "OFFER"},
                    "_id = ?",
                    new String[]{Integer.toString(id)},
                    null, null, null);
            cursor.moveToFirst();
            ((TextView) findViewById(R.id.itemDetailTitle)).setText(cursor.getString(FilmDataHelper.filmTable.NAME));
            this.currentItems = cursor.getInt(FilmDataHelper.filmTable.BOUGHT);
            ((TextView) findViewById(R.id.itemDetailDescription)).setText(cursor.getString(FilmDataHelper.filmTable.DESC));
            ((TextView) findViewById(R.id.itemDetailPrice)).setText(cursor.getString(FilmDataHelper.filmTable.PRICE));
            if (cursor.getString(FilmDataHelper.filmTable.PLATFORM).equals("dvd"))
                ((TextView) findViewById(R.id.itemDetailPlatform)).setText("DvD");
            else
                ((TextView) findViewById(R.id.itemDetailPlatform)).setText("BlueRay");
            String imageId = cursor.getString(FilmDataHelper.filmTable.IMAGE_ID);
            int imageIdSourced = getResources().getIdentifier(imageId, "drawable", getPackageName());
            if (String.valueOf(imageIdSourced).equals("0")) {
                ((ImageView) findViewById(R.id.itemDetailmage)).setImageResource(getResources().getIdentifier("default_white", "drawable", getPackageName()));
            } else {
                ((ImageView) findViewById(R.id.itemDetailmage)).setImageResource(imageIdSourced);
            }
            int isNew = cursor.getInt(FilmDataHelper.filmTable.IS_NEW);
            if (isNew == 0) {
                ((ImageView) findViewById(R.id.itemDetailNew)).setImageResource(getResources().getIdentifier("watch_off", "drawable", getPackageName()));
            } else {
                ((ImageView) findViewById(R.id.itemDetailNew)).setImageResource(getResources().getIdentifier("watch_on", "drawable", getPackageName()));
            }
            int isOffer = cursor.getInt(FilmDataHelper.filmTable.OFFER);
            if (isOffer == 0) {
                ((ImageView) findViewById(R.id.itemDetailOffer)).setImageResource(getResources().getIdentifier("offer_off", "drawable", getPackageName()));
            } else {
                ((ImageView) findViewById(R.id.itemDetailOffer)).setImageResource(getResources().getIdentifier("offer_on", "drawable", getPackageName()));
            }
        } catch (Exception e) {
            Log.e("filmDetail", "catched");
        }
        updateItemsBought();
        Button addFilmButton = findViewById(R.id.itemDetailAdd);
        Button removeFilmButton = findViewById(R.id.itemDetailRemove);
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
    }

    private void changeNumberFilm(int sign) {
        int nextItems;
        ContentValues filmValues = new ContentValues();
        switch (sign){
            case 1:
                nextItems = currentItems + 1;
                break;
            case -1:
                if(this.currentItems > 0)
                    nextItems = currentItems - 1;
                else
                    nextItems = 0;
                break;
            default:
                nextItems = currentItems;
        }
        Log.e("filmDetail", "old number of items: " + String.valueOf(currentItems));
        Log.e("filmDetail", "new number of items: " + String.valueOf(nextItems));
        filmValues.put("BOUGHT", nextItems);
        SQLiteOpenHelper filmDbHelper = new FilmDataHelper(this);
        SQLiteDatabase db = filmDbHelper.getReadableDatabase();
        db.update("FILMS",
                filmValues,
                "_id = ?",
                new String[] {Integer.toString(this.id)});
        this.currentItems = nextItems;
        updateItemsBought();
    }

    private void updateItemsBought(){
        ((TextView) findViewById(R.id.itemDetailBought)).setText(String.valueOf(this.currentItems));
    }
}
/*db.execSQL("CREATE TABLE FILMS ("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "NAME TEXT, "
                + "DESCRIPTION TEXT, "
                + "PRICE FLOAT, "
                + "PLATFORM TEXT, "
                + "IMAGE_ID INTEGER, "
                + "IS_NEW BIT, "
                + "BOUGHT INTEGER, "
                + "OFFER BIT); ");*/
