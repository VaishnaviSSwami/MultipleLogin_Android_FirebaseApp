package com.example.foods;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class chef_view_menu extends AppCompatActivity {

    private TextView food_name, desc_name, quantity_value, prize_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chef_view_menu);
        food_name = findViewById(R.id.food_name);
        desc_name = findViewById(R.id.desc_name);
        quantity_value = findViewById(R.id.quantity_value);
        prize_name = findViewById(R.id.prize_name);


        String  cheffood = getIntent().getStringExtra("Foodkanam");
        String chefdesc = getIntent().getStringExtra("desckanam");
        String chefquant = getIntent().getStringExtra("quantitykanam");
        String foodprize = getIntent().getStringExtra("pricekanam");

        food_name.setText(cheffood);
        desc_name.setText(chefdesc);
        quantity_value.setText(chefquant);
        prize_name.setText(foodprize);

    }
}
