package com.example.practica2.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class FilmDataHelper extends SQLiteOpenHelper {
    private static final String DBNAME = "filmdb";
    private static final int DBVERSION = 3;
    public interface filmTable{
        int ID = 0;
        int NAME = 1;
        int DESC = 2;
        int PRICE = 3;
        int PLATFORM = 4;
        int IMAGE_ID = 5;
        int IS_NEW = 6;
        int OFFER = 7;
    };

    public FilmDataHelper(Context context) {
        super(context, DBNAME, null, DBVERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE FILMS ("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "NAME TEXT, "
                + "DESCRIPTION TEXT, "
                + "PRICE FLOAT, "
                + "PLATFORM TEXT, "
                + "IMAGE_ID INTEGER, "
                + "IS_NEW BIT, "
                + "OFFER BIT); ");
        /*
        addFilm(db, "Melende", "Melende", (float) 2.25, "dvd", 0, true, true);
        addFilm(db, "Home Alone", "El nene se ha quedao en casa solo por navidad", (float) 0.25, "dvd", 0, false, false);
        addFilm(db, "The Boss Baby", "HAHAHAHHAHA", (float) 2000000.00, "blueray", 0, false, false);
        addFilm(db, "Baby Driver", "Pues hay un coche rojo que da vueltas", (float) 19.50, "blueray", 0, false, true)*/
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        addFilm(db, "Melende", "Melende", (float) 2.25, "dvd", 0, true, true);
        addFilm(db, "Home Alone", "El nene se ha quedao en casa solo por navidad", (float) 0.25, "dvd", 0, false, false);
        addFilm(db, "The Boss Baby", "HAHAHAHHAHA", (float) 2000000.00, "blueray", 0, false, false);
        addFilm(db, "Baby Driver", "Pues hay un coche rojo que da vueltas", (float) 19.50, "blueray", 0, false, true);
    }

    public static void addFilm (SQLiteDatabase db, String name, String desc, float price, String platform, int imageID, boolean isNew, boolean offer)
    {
        ContentValues filmData = new ContentValues();
        filmData.put("NAME", name);
        filmData.put("DESCRIPTION", desc);
        filmData.put("PRICE", price);
        filmData.put("PLATFORM", platform);
        filmData.put("IMAGE_ID", imageID);
        filmData.put("IS_NEW", isNew);
        filmData.put("OFFER", offer);
        db.insert("FILMS", null, filmData);
    }

}
