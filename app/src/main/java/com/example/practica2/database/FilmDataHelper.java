package com.example.practica2.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class FilmDataHelper extends SQLiteOpenHelper {
    private static final String DBNAME = "filmdb";
    private static final int DBVERSION = 1;

    public interface filmTable {
        int ID = 0;
        int NAME = 1;
        int DESC = 2;
        int PRICE = 3;
        int PLATFORM = 4;
        int IMAGE_ID = 5;
        int IS_NEW = 6;
        int BOUGHT = 7;
        int OFFER = 8;
    }

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
                + "IMAGE_ID TEXT, "
                + "IS_NEW BIT, "
                + "BOUGHT INTEGER, "
                + "OFFER BIT); ");
        addFilm(db, "Melende", "Melende es un Melende producido por Melende sobre Melende en Melende. La verdad es que le damos un Melende sobre Melende.", (float) 2.25, "dvd", "melende", true, true, 1);
        addFilm(db, "Home Alone", "El nene se ha quedao en casa solo por navidad y le gusta pegar palizas a gente de clase baja.", (float) 0.25, "dvd", "home_alone", false, false, 2);
        addFilm(db, "The Boss Baby", "Recordatorio: no mirar esta pelicula en jordano subtitulado al japones.", (float) 2000000.00, "blueray", "the_boss_baby", false, false, 1);
        addFilm(db, "Baby Driver", "Pues hay un coche rojo que da vueltas y el que lo lleva escucha musica de chill.", (float) 19.50, "blueray", "baby_driver", false, true, 10);
        addFilm(db, "Django desencadenado", "Un vaquero que mata esclavistas.", (float) 12.25, "blueray", "django_desencadenado", false, true, 1);
        addFilm(db, "Cuchi Cuchi Official Video", "Charo and the Salsoul orchestra presenta el hit del siglo XX: el Cuchi-Cuchi.", (float) 19.99, "dvd", "cuchi_cuchi_official_video", false, false, 1);
        addFilm(db, "Parasite", "Koreanos haciendo cosas.", (float) 20.00, "blueray", "parasite", true, false, 2);
        addFilm(db, "Godzilla vs Kong", "Pues hay un gorila y un lagarto que se dan de ostias asi como ieepa suuumbaaa iupaaa y al final sale un Mechagodzilla que hace fuuaum.", (float) 23.75, "blueray", "godzilla_vs_kong", true, true, 3);
        addFilm(db, "Titanic", "Hay un barco que va contra un ielo y se cae y se unde la gente un poco. Rose es egosita.", (float) 5.00, "dvd", "titanic", true, true, 4);
        addFilm(db, "Arrival", "Aliens sordomudos en tu zona quieren conocerte.", (float) 5.00, "dvd", "arrival", true, true, 0);
        addFilm(db, "Oxygen", "Esta no la he visto pero creo que va de respirar y acordarse de cosas, aunque bueno es original de Netflix asi que el liston esta bajito.", (float) 15.00, "dvd", "oxygen", true, false, 0);
        addFilm(db, "Terminator", "No necesita descripcion osea es como un robot con escopetas que dice cosas en otros idiomas como arigato bebe o hasta luego crack.", (float) 25.00, "blueray", "terminator", false, true, 0);
        addFilm(db, "Avatar 2", "No ha salido pero seguro que vuelve a ser una historia en plan tecnho-Pocahontas con movidas ecologistas otra vez pero bajo el agua.", (float) 7.00, "blueray", "avatar_2", true, false, 0);
        addFilm(db, "Pacific Rim", "Es Transformers pero mejor que Transformers.", (float) 17.00, "dvd", "pacific_rim", true, true, 0);
        addFilm(db, "Fast and Furious: Tokyo Drift", "La mejor de toda la saga de enfados repentinos.", (float) 27.00, "dvd", "fast_and_furious_tokyo_drift", false, false, 0);
        addFilm(db, "The Greatest Showman", "Un tio se hace un circo y cantan cosas.", (float) 6.00, "blueray", "the_greatest_showman", true, true, 0);
        addFilm(db, "Tenet", "Es una pelicula muy interesante y etnaseretni yum alucilep anu se", (float) 19.00, "blueray", "tenet", true, false, 0);
        addFilm(db, "Gemini Man", "Will Smith contra Will Smith para salvar al mundo de que Will Smith haga muchos Will Smith para conquistar el mundo con Will Smith.", (float) 23.00, "dvd", "gemini_man", false, true, 0);
        addFilm(db, "I am mother", "Osea el robot hace cosas malas pero tienen un sentido en el fondo comprensible aunque el bunker es un poco claustrofobico.", (float) 22.00, "dvd", "i_am_mother", true, false, 0);
        addFilm(db, "1917", "Ladescripcionvatodadeunatiradacomolapeliqueestamuyguay.", (float) 21.00, "blueray", "_1917", true, true, 0);
        addFilm(db, "Knives out", "De las mejores pelis de misterio que han salido desde la de Sherlock Holmes con Tony Stark.", (float) 14.00, "blueray", "knives_out", false, false, 0);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion == 1 && newVersion == 2) {
            Log.d("OLD VERSION", "oldVersion " + oldVersion + " newVersion " + newVersion);
        }
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion == 1) {
            Log.d("OLD VERSION", "oldVersion " + oldVersion + " newVersion " + newVersion);
        }
    }

    public static void addFilm(SQLiteDatabase db, String name, String desc, float price, String platform, String imageID, boolean isNew, boolean offer, int bought) {
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