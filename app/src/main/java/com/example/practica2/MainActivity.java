package com.example.practica2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.practica2.cart.ShoppingCart;
import com.example.practica2.contact.contactOption;
import com.example.practica2.pages.CategoriesPagesAdapter;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Toolbar---
        androidx.appcompat.widget.Toolbar tool = findViewById(R.id.toolbar);
        setSupportActionBar(tool);

        //Sidemenu---
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                tool,
                0,
                0
        );
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigationdrawer_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Viewpager
        CategoriesPagesAdapter adapter = new CategoriesPagesAdapter(getSupportFragmentManager(), this);
        this.pager = findViewById(R.id.viewpager);
        pager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(pager);
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu)
    {
        getMenuInflater().inflate(R.menu.main_menu_mainactivity, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item)
    {
        //
        if (item.getItemId() == R.id.action_open_cart) {
            Intent intent = new Intent(this, ShoppingCart.class);
            startActivity(intent);
            return true;
        }
        return false;
    }

    @Override
    public void onBackPressed()
    {
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        if (drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else
        {
            super.onBackPressed();
        }
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();
        Intent intent;
        switch (id)
        {
            case R.id.navigation_home:
                this.pager.setCurrentItem(0);
                break;
            case R.id.navigation_offer:
                this.pager.setCurrentItem(1);
            break;
            case R.id.navigation_calendar:
                this.pager.setCurrentItem(2);
                break;
            case R.id.navigation_dvd:
                this.pager.setCurrentItem(3);
                break;
            case R.id.navigation_blueray:
                this.pager.setCurrentItem(4);
            break;
            case R.id.navigation_cart:
                intent = new Intent(this, ShoppingCart.class);
                startActivity(intent);
                break;
            case R.id.navigation_help:
                break;
            case R.id.navigation_contact:
                intent = new Intent(this, contactOption.class);
                startActivity(intent);
                break;
            case R.id.navigation_find_us:
                break;
        }
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

}