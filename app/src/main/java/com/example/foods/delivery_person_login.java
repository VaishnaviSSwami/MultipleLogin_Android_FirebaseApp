package com.example.foods;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class delivery_person_login extends AppCompatActivity {
    EditText phone_no;
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
               if(!phone_no.getText().toString().trim().isEmpty()){
                   if((phone_no.getText().toString().trim()).length()==10){
                       Intent i=new Intent(getApplicationContext(),delivery_person_otp_verification.class);
                       getIntent().putExtra("mobile",phone_no.getText().toString());
                       startActivity(i);
                       Toast.makeText(delivery_person_login.this, "OTP SENT SUCCESSFULLY", Toast.LENGTH_SHORT).show();
                   }
                   else{
                       Toast.makeText(delivery_person_login.this, "PLEASE ENTER CORRECT MOBILE NUMBER", Toast.LENGTH_SHORT).show();
                   }
               }
               else{
                   Toast.makeText(delivery_person_login.this, "PLEASE ENTER THE PHONE NUMBER", Toast.LENGTH_SHORT).show();
               }
            }
        });


    }
}