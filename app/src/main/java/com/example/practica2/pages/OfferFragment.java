package com.example.practica2.pages;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.SimpleCursorAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.ListFragment;

import com.example.practica2.R;
import com.example.practica2.database.FilmDataHelper;

public class OfferFragment extends ListFragment {
    public OfferFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FilmDataHelper filmDbHelper = new FilmDataHelper(getContext());
        SQLiteDatabase db = filmDbHelper.getReadableDatabase();
        Cursor cursor = db.query("FILMS",
                new String[] {"_id", "NAME", "PRICE", "BOUGHT"},
                "offer = ?",
                new String[] {"1"},
                null,
                null,
                null);
        cursor.moveToFirst();
        SimpleCursorAdapter listAdapter = new SimpleCursorAdapter(
                getContext(),
                R.layout.item_list,
                cursor,
                new String[]{"NAME", "PRICE", "BOUGHT"},
                new int[] {R.id.itemTitle, R.id.itemPrice, R.id.itemBought},
                0);
        setListAdapter(listAdapter);
        // Inflate the layout for this fragment
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
