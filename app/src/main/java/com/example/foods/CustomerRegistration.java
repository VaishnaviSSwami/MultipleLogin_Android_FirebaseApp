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


public class CustomerRegistration extends AppCompatActivity {
    EditText phone_no;
    Button btn_otp;
    ProgressBar pao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_registration);
        phone_no = findViewById(R.id.inputphno);
        btn_otp=findViewById(R.id.buttongetotp);
        pao =findViewById(R.id.pgid);
        btn_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!phone_no.getText().toString().trim().isEmpty()){
                    if((phone_no.getText().toString().trim()).length()==10){

                        pao.setVisibility(View.VISIBLE);
                        btn_otp.setVisibility(View.INVISIBLE);

                        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                                "+91" + phone_no.getText().toString(),
                                10,
                                TimeUnit.SECONDS,
                                CustomerRegistration.this,

                                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                    @Override
                                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                                        pao.setVisibility(View.GONE);
                                        btn_otp.setVisibility(View.VISIBLE);
                                    }

                                    @Override
                                    public void onVerificationFailed(@NonNull FirebaseException e) {
                                        pao.setVisibility(View.GONE);
                                        btn_otp.setVisibility(View.VISIBLE);
                                        Toast.makeText(CustomerRegistration.this, "Please check Your Internet Connection", Toast.LENGTH_SHORT).show();

                                    }

                                    @Override
                                    public void onCodeSent(@NonNull String databse_otp, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                        pao.setVisibility(View.GONE);
                                        btn_otp.setVisibility(View.VISIBLE);
                                        Intent i=new Intent(getApplicationContext(),customer_login.class);
                                        getIntent().putExtra("mobile",phone_no.getText().toString());
                                        getIntent().putExtra("databse_otp",databse_otp);
                                        startActivity(i);
                                    }
                                }
                        );

                     /*  Intent i=new Intent(getApplicationContext(),customer_login.class);
                       getIntent().putExtra("mobile",phone_no.getText().toString());
                       startActivity(i);*/

                        Toast.makeText(CustomerRegistration.this, "OTP SENT SUCCESSFULLY", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(CustomerRegistration.this, "PLEASE ENTER CORRECT MOBILE NUMBER", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(CustomerRegistration.this, "PLEASE ENTER THE PHONE NUMBER", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}