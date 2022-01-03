package com.example.foods;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Chef_Dashboard extends AppCompatActivity {
    ImageView food_image;
    Button Btn_ADD_MENU,Btn_VIEW_MENU;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chef_dashboard);
        Button btn_ADD_MENU;
        btn_ADD_MENU=findViewById(R.id.btn_ADD_MENU);
        btn_ADD_MENU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Chef_Dashboard.this,chef_add_menu.class);
                startActivity(i);
            }
        });
        Button btn_VIEW_MENU;
        btn_VIEW_MENU=findViewById(R.id.btn_VIEW_MENU);
        btn_VIEW_MENU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Chef_Dashboard.this, chef_view_menu.class);
                startActivity(i);
            }
        });
    }

}