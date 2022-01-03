package com.example.foods;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class chef_add_menu extends AppCompatActivity {
    private EditText name_of_food, desco , quanto, prize;
    private Button btn_ADD_MENU_submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chef_add_menu);
                name_of_food = findViewById(R.id.name_of_food);
                desco = findViewById(R.id.desco);
                quanto = findViewById(R.id.quanto);
                prize = findViewById(R.id.prize);
        btn_ADD_MENU_submit = findViewById(R.id.btn_ADD_MENU_submit);
        btn_ADD_MENU_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cheffood = name_of_food.getText().toString();
                String chefdesc = desco.getText().toString();
                String chefquant = quanto.getText().toString();
                String foodprize = prize.getText().toString();
                Intent i4 = new Intent(chef_add_menu.this,chef_view_menu.class);

                i4.putExtra("Foodkanam",cheffood);
                i4.putExtra("desckanam",chefdesc);
                i4.putExtra("quantitykanam",chefquant);
                i4.putExtra("pricekanam",foodprize);
                startActivity(i4);
            }
        });

    }
}
