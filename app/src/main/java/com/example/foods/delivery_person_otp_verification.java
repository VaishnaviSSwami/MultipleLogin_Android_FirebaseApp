package com.example.foods;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foods.databinding.ActivityDeliveryPersonOtpVerificationBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class delivery_person_otp_verification extends AppCompatActivity {
    private ActivityDeliveryPersonOtpVerificationBinding binding;
    private String VerificationId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDeliveryPersonOtpVerificationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        otpinput();

        binding.phoneNoShowOtp.setText(String.format(
                "+91-%s",getIntent().getStringExtra("mobile")
        ));

        VerificationId = getIntent().getStringExtra("VerificationId");

        binding.resendOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(delivery_person_otp_verification.this, "OTP Send Successfully", Toast.LENGTH_SHORT).show();
            }
        });
        binding.btnOtpSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.progressBarVerifyOtp.setVisibility(View.VISIBLE);
                binding.btnOtpSubmit.setVisibility(View.INVISIBLE);
                if (binding.otp1.getText().toString().trim().isEmpty() ||
                binding.otp2.getText().toString().trim().isEmpty() ||
                binding.otp3.getText().toString().trim().isEmpty() ||
                binding.otp4.getText().toString().trim().isEmpty() ||
                binding.otp5.getText().toString().trim().isEmpty() ||
                binding.otp6.getText().toString().trim().isEmpty()){
                    Toast.makeText(delivery_person_otp_verification.this, "OTP is Not Valid", Toast.LENGTH_SHORT).show();
                }
                else{
                    if (VerificationId != null){
                        String code = binding.otp1.getText().toString().trim() +
                                binding.otp2.getText().toString().trim() +
                                binding.otp3.getText().toString().trim() +
                                binding.otp4.getText().toString().trim() +
                                binding.otp5.getText().toString().trim() +
                                binding.otp6.getText().toString().trim();
                        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(VerificationId,code);
                        FirebaseAuth.getInstance().signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){
                                    binding.progressBarVerifyOtp.setVisibility(View.VISIBLE);
                                    binding.btnOtpSubmit.setVisibility(View.INVISIBLE);
                                    Toast.makeText(delivery_person_otp_verification.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                    Intent intent =new Intent(delivery_person_otp_verification.this,delivery_person_dashboard.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent);
                                }else{
                                    binding.progressBarVerifyOtp.setVisibility(View.GONE);
                                    binding.btnOtpSubmit.setVisibility(View.VISIBLE);
                                    Toast.makeText(delivery_person_otp_verification.this, "OTP Is Not Valid", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                }
            }
        });
    }

    private void otpinput() {
        binding.otp1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
               binding.otp2.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        binding.otp2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                binding.otp3.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        binding.otp3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                binding.otp4.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        binding.otp4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                binding.otp5.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        binding.otp5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                binding.otp6.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}