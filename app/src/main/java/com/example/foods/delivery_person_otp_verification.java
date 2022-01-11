package com.example.foods;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class delivery_person_otp_verification extends AppCompatActivity {
    TextView phone_no_show_otp;
    EditText otp1;
    EditText otp2;
    EditText otp3;
    EditText otp4;
    TextView resend_otp;
    Button btn_otp_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_person_otp_verification);
        otp1 =findViewById(R.id.otp1);
        otp2 =findViewById(R.id.otp2);
        otp3 =findViewById(R.id.otp3);
        otp4 =findViewById(R.id.otp4);
        btn_otp_submit =findViewById(R.id.btn_otp_submit);


        TextView textView =findViewById(R.id.phone_no_show_otp);
        textView.setText(String.format(
                "+91-%s",getIntent().getStringExtra("mobile")
        ));
       btn_otp_submit.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if (!otp1.getText().toString().trim().isEmpty()){
                   Toast.makeText(delivery_person_otp_verification.this, "OTP Verified Successfully", Toast.LENGTH_SHORT).show();
               }
               else{
                   Toast.makeText(delivery_person_otp_verification.this, "Please Enter Correct OTP", Toast.LENGTH_SHORT).show();
               }
           }
       });
       otpmove();


    }

    private void otpmove() {
      otp1.addTextChangedListener(new TextWatcher() {
          @Override
          public void beforeTextChanged(CharSequence s, int start, int count, int after) {

          }

          @Override
          public void onTextChanged(CharSequence s, int start, int before, int count) {
              if (!s.toString().trim().isEmpty()){
                  otp2.requestFocus();
              }

          }

          @Override
          public void afterTextChanged(Editable s) {

          }
      });
        otp2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()){
                    otp3.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        otp3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()){
                    otp4.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}