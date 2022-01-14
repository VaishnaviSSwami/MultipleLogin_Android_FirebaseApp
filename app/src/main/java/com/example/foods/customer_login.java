package com.example.foods;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.foods.databinding.ActivityCustomerLoginBinding;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class customer_login extends AppCompatActivity {
    private ActivityCustomerLoginBinding binding;
    private FirebaseAuth mAuth;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallback;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCustomerLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mAuth = FirebaseAuth.getInstance();

        binding.btnOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.phoneNo.getText().toString().trim().isEmpty()){
                    Toast.makeText(customer_login.this, "Invalid Phone No.", Toast.LENGTH_SHORT).show();
                }if (binding.phoneNo.getText().toString().trim().length() !=10){
                    Toast.makeText(customer_login.this, "Enter Valid Phone no.", Toast.LENGTH_SHORT).show();
                }
                else{
                    otpsend();
                }
            }
        });
    }

    private void otpsend() {
        binding.progressBarSendingOtp.setVisibility(View.VISIBLE);
        binding.btnOtp.setVisibility(View.INVISIBLE);

        mCallback= new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {
                binding.progressBarSendingOtp.setVisibility(View.GONE);
                binding.btnOtp.setVisibility(View.VISIBLE);
                Toast.makeText(customer_login.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onCodeSent(@NonNull String VerificationId,
                                   @NonNull PhoneAuthProvider.ForceResendingToken token){
                binding.progressBarSendingOtp.setVisibility(View.GONE);
                binding.btnOtp.setVisibility(View.VISIBLE);
                Toast.makeText(customer_login.this, "OTP Sent Successfully", Toast.LENGTH_SHORT).show();
                Intent intent =new Intent(customer_login.this,CustomerRegistration.class);
                intent.putExtra("mobile",binding.phoneNo.getText().toString().trim());
                intent.putExtra("VerificationId",VerificationId);
                startActivity(intent);
            }
        };
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber("+91" + binding.phoneNo.getText().toString().trim())
                        .setTimeout(10L, TimeUnit.SECONDS)
                        .setActivity(this)
                        .setCallbacks(mCallback)
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }
}