package com.example.practica2.pages;


import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.practica2.R;

public class CategoriesPagesAdapter extends FragmentPagerAdapter {
    private Context m_context;

    public CategoriesPagesAdapter(FragmentManager fragmentManager, Context context) {
        super(fragmentManager);
        this.m_context = context;
    }

    @Override
    public int getCount() {
        return 5;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Log.e("adios", String.valueOf(position));
        switch (position) {
            case 0:
                return new HomeFragment();
            case 1:
                return new OfferFragment();
            case 2:
                return new LatestFragment();
            case 3:
                return new DvdFragment();
            case 4:
                return new BluerayFragment();
            default:
                return new HomeFragment();
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        Log.e("Hola", String.valueOf(position));
        switch (position) {
            case 0:
                return m_context.getResources().getText(R.string.home_tab);
            case 1:
                return m_context.getResources().getText(R.string.offers_tab);
            case 2:
                return m_context.getResources().getText(R.string.latest_tab);
            case 3:
                return m_context.getResources().getText(R.string.dvd_tab);
            case 4:
                return m_context.getResources().getText(R.string.blueray_tab);
        }
        return null;
    }
}