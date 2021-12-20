package com.example.foods;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class Splash_Screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Thread thread=new Thread() {
            public void run() {
                try {
                    sleep(3000);

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    Intent i = new Intent(Splash_Screen.this,home.class);
                    startActivity(i);

                }
            }

        };thread.start();
    }
    }
