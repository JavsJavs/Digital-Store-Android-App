package com.example.practica2.pages;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.ListFragment;

import com.example.practica2.R;
import com.example.practica2.database.FilmDataHelper;

public class DvdFragment extends ListFragment{
    public DvdFragment() {
        // Required empty public constructor
    }

    private LayoutInflater inflater;
    private ViewGroup container;
    private Bundle savedInstanceState;

    /*Button addProductButton = findViewById(R.id.contactButton);
        addProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contactClick(v);
            }
        });*/

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.inflater = inflater;
        this.container = container;
        this.savedInstanceState = savedInstanceState;
        FilmDataHelper filmDbHelper = new FilmDataHelper(getContext());
        SQLiteDatabase db = filmDbHelper.getReadableDatabase();
        Cursor cursor = db.query("FILMS",
                new String[]{"_id", "NAME", "PRICE", "BOUGHT"},
                "platform = ?",
                new String[]{"dvd"},
                null,
                null,
                null);
        cursor.moveToFirst();
        /*
        for(int i = 0; i < cursor.getCount(); i ++){
            Log.e("Ruben", cursor.getString(FilmDataHelper.filmTable.NAME));
            cursor.moveToNext();
        }
        */
        SimpleCursorAdapter listAdapter = new SimpleCursorAdapter(
                getContext(),
                R.layout.item_list,
                cursor,
                new String[]{"NAME", "PRICE", "BOUGHT"},
                new int[]{R.id.itemTitle, R.id.itemPrice, R.id.itemBought},
                0);
        setListAdapter(listAdapter);
        // Inflate the layout for this fragment
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Log.e("DvdFragment", "item clicked");
        super.onListItemClick(l, v, position, id);
        this.onCreateView(this.inflater, this.container, this.savedInstanceState);
        Intent intent = new Intent(getActivity(), FilmDetail.class);
        try {
            intent.putExtra("FilmId", String.valueOf(id));
            startActivity(intent);
        } catch (Exception e) {
            Log.e("DvdFragment", "Exception");
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