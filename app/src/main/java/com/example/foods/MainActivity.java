package com.example.foods;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;
import java.io.File;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    ActionBarDrawerToggle toggle;


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.navigation_view);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.navigation_open, R.string.navigation_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.chef:
                        Log.i("Menu_drawer", "Chef is selected ");
                        Intent i=new Intent(MainActivity.this,Chef_Login.class);
                        startActivity(i);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.customer:
                        Log.i("Menu_drawer", "Customer is selected ");
                        Intent intent=new Intent(MainActivity.this,customer_login.class);
                        startActivity(intent);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.food_warrior:
                        Log.i("Menu_drawer","Food warrior is selected ");
                        Intent intent7=new Intent(MainActivity.this,delivery_person_login.class);
                        startActivity(intent7);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.about:
                        Log.i("Menu_drawer", "About is selected ");
                        Intent intent1=new Intent(MainActivity.this,about_us.class);
                        startActivity(intent1);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.contact_us:
                        Log.i("Menu_drawer", "Contact_us is selected ");
                        Intent intent3=new Intent(MainActivity.this,contact_us.class);
                        startActivity(intent3);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.gallery:
                        Log.i("Menu_drawer", "Gallery is selected ");
                        Intent intent4=new Intent(MainActivity.this,gallery.class);
                        startActivity(intent4);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.share:
                        Log.i("Menu_drawer", "Share is selected ");
                        Intent intent5=new Intent(Intent.ACTION_SEND);
                        intent5.setType("text/plain");
                        String Body=" Download this app";
                        String sub="http://googleplaystore/bellifood";
                        intent5.putExtra(Intent.EXTRA_TEXT,Body);
                        intent5.putExtra(Intent.EXTRA_TEXT,sub);

                        startActivity(Intent.createChooser(intent5,"share using"));
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                }
                return true;
            }
        });

    }
}

