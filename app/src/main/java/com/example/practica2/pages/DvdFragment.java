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

public class DvdFragment extends ListFragment {
    public DvdFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FilmDataHelper filmDbHelper = new FilmDataHelper(getContext());
        SQLiteDatabase db = filmDbHelper.getReadableDatabase();
        Cursor cursor = db.query("FILMS",
                new String[] {"_id", "NAME"},
                "platform = ?",
                new String[] {"dvd"},
                null,
                null,
                null);
        cursor.moveToFirst();
        for(int i = 0; i < cursor.getCount(); i ++){
            Log.e("Ruben", cursor.getString(FilmDataHelper.filmTable.NAME));
            cursor.moveToNext();
        }
        SimpleCursorAdapter listAdapter = new SimpleCursorAdapter(
                getContext(),
                android.R.layout.simple_list_item_1,
                cursor,
                new String[]{"NAME"},
                new int[] {android.R.id.text1},
                0);
        setListAdapter(listAdapter);
        // Inflate the layout for this fragment
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}

