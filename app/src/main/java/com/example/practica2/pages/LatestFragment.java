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

public class LatestFragment extends ListFragment {
    public LatestFragment() {
        // Required empty public constructor
    }

    private Cursor cursor;
    private SQLiteDatabase db;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FilmDataHelper filmDbHelper = new FilmDataHelper(getContext());
        this.db = filmDbHelper.getReadableDatabase();
        this.cursor = db.query("FILMS",
                new String[]{"_id", "NAME", "PRICE", "BOUGHT"},
                "is_new = ?",
                new String[]{"1"},
                null,
                null,
                null);
        cursor.moveToFirst();
        SimpleCursorAdapter listAdapter = new SimpleCursorAdapter(
                getContext(),
                R.layout.item_list,
                cursor,
                new String[]{"NAME", "PRICE", "BOUGHT"},
                new int[]{R.id.itemTitle, R.id.itemPrice, R.id.itemBought},
                0);
        setListAdapter(listAdapter);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Intent intent = new Intent(getActivity(), FilmDetail.class);
        try {
            intent.putExtra("FilmId", String.valueOf(id));
            startActivity(intent);
        } catch (Exception e) {
            Log.e("LatestFragment", "Exception");
        }
    }
}