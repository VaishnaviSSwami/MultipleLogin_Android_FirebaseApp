package com.example.foods;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.io.File;

public class home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
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
            Intent intent=new Intent(home.this,about_us.class);
            startActivity(intent);
        }
        else if (id==R.id.gallery)
        {
            Intent intent=new Intent(home.this,gallery.class);
            startActivity(intent);
        }
        else if (id==R.id.chef)
        {
            Intent intent=new Intent(home.this,cheif_login.class);
            startActivity(intent);
        }
        else if (id==R.id.customer)
        {
            Intent intent=new Intent(home.this,customer_login.class);
            startActivity(intent);
        }
        else if (id==R.id.contact_us)
        {
            Intent intent=new Intent(home.this,contact_us.class);
            startActivity(intent);
        }
        return true;
    }

    public void setContentView(int activity_home) {
    }
}