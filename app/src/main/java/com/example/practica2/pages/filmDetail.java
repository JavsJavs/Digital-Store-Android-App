package com.example.practica2.pages;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.practica2.R;
import com.example.practica2.database.FilmDataHelper;

public class filmDetail extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_detail);

        int id = Integer.parseInt(getIntent().getStringExtra("FilmId"));
        SQLiteOpenHelper filmDbHelper = new FilmDataHelper(this) ;
        try {
            SQLiteDatabase db = filmDbHelper.getReadableDatabase();
            Cursor cursor = db.query("FILMS",
                    new String[]{"_id", "NAME", "DESCRIPTION", "PRICE", "PLATFORM", "IMAGE_ID", "IS_NEW", "BOUGHT", "OFFER"},
                    "_id = ?",
                    new String[]{Integer.toString(id)},
                    null, null, null);
            cursor.moveToFirst();
            ((TextView)findViewById(R.id.itemDetailTitle)).setText(cursor.getString(FilmDataHelper.filmTable.NAME));
            ((TextView)findViewById(R.id.itemDetailDescription)).setText(cursor.getString(FilmDataHelper.filmTable.DESC));
            ((TextView)findViewById(R.id.itemDetailPrice)).setText(cursor.getString(FilmDataHelper.filmTable.PRICE));
            ((TextView)findViewById(R.id.itemDetailPlatform)).setText(cursor.getString(FilmDataHelper.filmTable.PLATFORM));

            int imageId = cursor.getInt(FilmDataHelper.filmTable.IMAGE_ID);
            Log.e("filmDetail imId", String.valueOf(imageId));
            if(imageId == 0){
                ((ImageView)findViewById(R.id.itemDetailmage)).setImageResource(getResources().getIdentifier("default_white", "drawable", getPackageName()));
            }else{
                ((ImageView)findViewById(R.id.itemDetailmage)).setImageResource(cursor.getInt(FilmDataHelper.filmTable.IMAGE_ID));
            }

            int isNew = cursor.getInt(FilmDataHelper.filmTable.IS_NEW);
            Log.e("filmDetail isNew", String.valueOf(isNew));
            if(isNew == 0){
                ((ImageView)findViewById(R.id.itemDetailNew)).setImageResource(getResources().getIdentifier("watch_black", "drawable", getPackageName()));
            }else{
                ((ImageView)findViewById(R.id.itemDetailNew)).setImageResource(getResources().getIdentifier("watch_white", "drawable", getPackageName()));
            }

            int isOffer = cursor.getInt(FilmDataHelper.filmTable.OFFER);
            Log.e("filmDetail isOf", String.valueOf(isOffer));
            if(isOffer == 0){
                ((ImageView)findViewById(R.id.itemDetailOffer)).setImageResource(getResources().getIdentifier("offer_black", "drawable", getPackageName()));
            }else{
                ((ImageView)findViewById(R.id.itemDetailOffer)).setImageResource(getResources().getIdentifier("offer_white", "drawable", getPackageName()));
            }
        }
        catch (Exception e){
        }

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
