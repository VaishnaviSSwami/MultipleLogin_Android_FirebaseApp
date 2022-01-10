package com.example.foods;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class delivery_person_login extends AppCompatActivity {
    EditText delivery_personPhone;
    Button btn_otp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_person_login);
        Button btn_otp;
        btn_otp=findViewById(R.id.btn_otp);
        btn_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(delivery_person_login.this,delivery_person_otp_verification.class);
                startActivity(i);
            }
        });


    }
}