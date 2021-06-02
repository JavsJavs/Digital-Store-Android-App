package com.example.practica2.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class FilmDataHelper extends SQLiteOpenHelper {
    private static final String DBNAME = "filmdb";
    private static final int DBVERSION = 1;
    public interface filmTable{
        int ID = 0;
        int NAME = 1;
        int DESC = 2;
        int PRICE = 3;
        int PLATFORM = 4;
        int IMAGE_ID = 5;
        int IS_NEW = 6;
        int BOUGHT = 7;
        int OFFER = 8;
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
                + "BOUGHT INTEGER, "
                + "OFFER BIT); ");
        /*
        addFilm(db, "Melende", "Melende", (float) 2.25, "dvd", 0, true, true);
        addFilm(db, "Home Alone", "El nene se ha quedao en casa solo por navidad", (float) 0.25, "dvd", 0, false, false);
        addFilm(db, "The Boss Baby", "HAHAHAHHAHA", (float) 2000000.00, "blueray", 0, false, false);
        addFilm(db, "Baby Driver", "Pues hay un coche rojo que da vueltas", (float) 19.50, "blueray", 0, false, true)*/

        addFilm(db, "Melende", "Melende", (float) 2.25, "dvd", 0, true, true, 1);
        addFilm(db, "Home Alone", "El nene se ha quedao en casa solo por navidad", (float) 0.25, "dvd", 0, false, false, 2);
        addFilm(db, "The Boss Baby", "Recordatorio: no mirar esta pelicula en jordanio subtitulado al japones", (float) 2000000.00, "blueray", 0, false, false, 1);
        addFilm(db, "Baby Driver", "Pues hay un coche rojo que da vueltas", (float) 19.50, "blueray", 0, false, true, 10);
        addFilm(db, "Django desencadenado", "Un vaquero que mata esclavistas", (float) 12.25, "blueray", 0, false, true, 1);
        addFilm(db, "Cuchi Cuchi Official Video", "Charo and the Salsoul orchestra", (float) 19.99, "dvd", 0, false, false, 01);
        addFilm(db, "Parasite", "Koreanos haciendo cosas", (float) 20.00, "blueray", 0, true, false, 02);
        addFilm(db, "Godzilla vs Kong", "Pues hay un gorila y un lagarto que se dan de ostias asi como ieepa suuumbaaa iupaaa", (float) 23.75, "blueray", 0, true, true, 3);
        addFilm(db, "Titanic", "Hay un barco que va contra un ielo y se cae y se unde la gente un poco", (float) 5.00, "dvd", 0, true, true, 4);
        addFilm(db, "Arrival", "Aliens sordomudos en tu zona quieren conocerte", (float) 5.00, "dvd", 0, true, true, 0);
        addFilm(db, "Oxygen", "Esta no la he visto pero creo que va de respirar y acordarse de cosas", (float) 15.00, "dvd", 0, true, false, 0);
        addFilm(db, "Terminator", "No necesita descripcion osea es como un robot con escopetas", (float) 25.00, "blueray", 0, false, true, 0);
        addFilm(db, "Avatar 2", "No ha salido pero seguro que vuelve a ser una historia en plan techo-Pocahontas con movidas ecologistas otra vez", (float) 7.00, "blueray", 0, true, false, 0);
        addFilm(db, "Pacific Rim", "Es Transformers pero mejor que Transformers", (float) 17.00, "dvd", 0, true, true, 0);
        addFilm(db, "Fast and Furious: Tokyo Drift", "La mejor de toda la saga de enfados repentinos", (float) 27.00, "dvd", 0, false, false, 0);
        addFilm(db, "The Greatest Showman", "Un tio se hace un circo y cantan cosas", (float) 6.00, "blueray", 0, true, true, 0);
        addFilm(db, "Tenet", "Es una pelicula muy interesante y etnaseretni yum alucilep anu se", (float) 19.00, "blueray", 0, true, false, 0);
        addFilm(db, "Gemini Man", "Will Smith contra Will Smith para salvar al mundo de que Will Smith haga muchos Will Smith para conquistar el mundo con Will Smith", (float) 23.00, "dvd", 0, false, true, 0);
        addFilm(db, "I am mother", "Osea el robot hace cosas malas pero tienen un sentido en el fondo comprensible aunque el bunker es un poco claustrofobico", (float) 22.00, "dvd", 0, true, false, 0);
        addFilm(db, "1917", "Ladescripcionvatodadeunatiradacomolapeliqueestamuyguay", (float) 21.00, "blueray", 0, true, true, 0);
        addFilm(db, "Knives out", "De las mejores pelis de misterio que han salido desde la de Sherlock Holmes con Tony Stark", (float) 14.00, "blueray", 0, false, false, 0);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public static void addFilm (SQLiteDatabase db, String name, String desc, float price, String platform, int imageID, boolean isNew, boolean offer, int bought)
    {
        ContentValues filmData = new ContentValues();
        filmData.put("NAME", name);
        filmData.put("DESCRIPTION", desc);
        filmData.put("PRICE", price);
        filmData.put("PLATFORM", platform);
        filmData.put("IMAGE_ID", imageID);
        filmData.put("IS_NEW", isNew);
        filmData.put("OFFER", offer);
        filmData.put("BOUGHT", bought);
        db.insert("FILMS", null, filmData);
    }

}
