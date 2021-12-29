package com.example.foods;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    NavigationView navigationView;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DrawerLayout drawerLayout = findViewById(R.id.drawer);
        navigationView=findViewById(R.id.navigation_view);




        ActionBarDrawerToggle toggle;
        toggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.navigation_open,R.string.navigation_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        setSupportActionBar(toolbar);
    }

    private void setSupportActionBar(Toolbar toolbar) {
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sidemenu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.share)
        {
            ApplicationInfo applicationInfo=getApplicationContext().getApplicationInfo();
            String apkpath=applicationInfo.sourceDir;
            Intent i=new Intent(Intent.ACTION_SEND);
            i.setType("application/vnd.android.pacakge-archieve");
            i.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(new File(apkpath)));
            startActivity(Intent.createChooser(i,"Share Via"));

        }
        else if (id==R.id.about)
        {
            Intent intent=new Intent(MainActivity.this,about_us.class);
            startActivity(intent);
        }
        else if (id==R.id.gallery)
        {
            Intent intent=new Intent(MainActivity.this,gallery.class);
            startActivity(intent);
        }
        else if (id==R.id.chef)
        {
            Intent intent=new Intent(MainActivity.this,cheif_login.class);
            startActivity(intent);
        }
        else if (id==R.id.customer)
        {
            Intent intent=new Intent(MainActivity.this,customer_login.class);
            startActivity(intent);
        }
        else if (id==R.id.contact_us)
        {
            Intent intent=new Intent(MainActivity.this,contact_us.class);
            startActivity(intent);
        }
        return true;
    }

    public void setContentView(int activity_home) {
    }
}