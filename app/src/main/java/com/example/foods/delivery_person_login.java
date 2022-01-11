package com.example.foods;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class delivery_person_login extends AppCompatActivity {
    EditText phone_no;
    Button btn_otp;
    ProgressBar progressBar_sending_otp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_person_login);
        Button btn_otp;
        btn_otp=findViewById(R.id.btn_otp);
        progressBar_sending_otp =findViewById(R.id.progressBar_sending_otp);
        btn_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(!phone_no.getText().toString().trim().isEmpty()){
                   if((phone_no.getText().toString().trim()).length()==10){

                       progressBar_sending_otp.setVisibility(View.VISIBLE);
                       btn_otp.setVisibility(View.INVISIBLE);

                       PhoneAuthProvider.getInstance().verifyPhoneNumber(
                               "+91" + phone_no.getText().toString(),
                               10,
                               TimeUnit.SECONDS,
                               delivery_person_login.this,
                               new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                   @Override
                                   public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                                       progressBar_sending_otp.setVisibility(View.GONE);
                                       btn_otp.setVisibility(View.VISIBLE);
                                   }

                                   @Override
                                   public void onVerificationFailed(@NonNull FirebaseException e) {
                                       progressBar_sending_otp.setVisibility(View.GONE);
                                       btn_otp.setVisibility(View.VISIBLE);
                                       Toast.makeText(delivery_person_login.this, "Please check Your Internet Connection", Toast.LENGTH_SHORT).show();

                                   }

                                   @Override
                                   public void onCodeSent(@NonNull String databse_otp, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                       progressBar_sending_otp.setVisibility(View.GONE);
                                       btn_otp.setVisibility(View.VISIBLE);
                                       Intent i=new Intent(getApplicationContext(),delivery_person_otp_verification.class);
                                       getIntent().putExtra("mobile",phone_no.getText().toString());
                                       getIntent().putExtra("databse_otp",databse_otp);
                                       startActivity(i);
                                   }
                               }
                       );
                     /*  Intent i=new Intent(getApplicationContext(),delivery_person_otp_verification.class);
                       getIntent().putExtra("mobile",phone_no.getText().toString());
                       startActivity(i);*/
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