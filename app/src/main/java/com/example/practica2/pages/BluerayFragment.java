package com.example.practica2.pages;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.fragment.app.ListFragment;

import com.example.practica2.R;

public class BluerayFragment extends ListFragment {
    public BluerayFragment() {
// Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                inflater.getContext(),
                android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.bluerays)
        );
        setListAdapter(adapter);
// Inflate the layout for this fragment
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
