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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class customer_login extends AppCompatActivity {
    EditText otp1;
    EditText otp2;
    EditText otp3;
    EditText otp4;
    EditText otp5;
    EditText otp6;
    TextView resend_otp;
    Button btn_otp_submit;
    String getotpbackend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_login);
        otp1 =findViewById(R.id.inputotp1);
        otp2 =findViewById(R.id.inputotp2);
        otp3 =findViewById(R.id.inputotp3);
        otp4 =findViewById(R.id.inputotp4);
        otp5 =findViewById(R.id.inputotp5);
        otp6 =findViewById(R.id.inputotp6);

        btn_otp_submit =findViewById(R.id.buttonsubmit);


        TextView textView =findViewById(R.id.textmobileshownumber);
        textView.setText(String.format(
                "+91-%s",getIntent().getStringExtra("mobile")
        ));

        getotpbackend=getIntent().getStringExtra("database_otp");
        final ProgressBar pandu =findViewById(R.id.ppppp);
        btn_otp_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!otp1.getText().toString().trim().isEmpty()  && !otp2.getText().toString().trim().isEmpty()  && !otp3.getText().toString().trim().isEmpty()  && !otp4.getText().toString().trim().isEmpty() && !otp5.getText().toString().trim().isEmpty() && !otp6.getText().toString().trim().isEmpty() ){
                    String enter_otp =otp1.getText().toString() +
                            otp2.getText().toString() +
                            otp3.getText().toString() +
                            otp4.getText().toString()+
                            otp5.getText().toString()+
                            otp6.getText().toString();

                    if (getotpbackend!= null){
                        pandu.setVisibility(View.VISIBLE);
                        btn_otp_submit.setVisibility(View.INVISIBLE);
                        PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(
                                getotpbackend,enter_otp
                        );
                        FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        pandu.setVisibility(View.GONE);
                                        btn_otp_submit.setVisibility(View.VISIBLE);

                                        if(task.isSuccessful()){
                                            Intent intent= new Intent(getApplicationContext(),customer_dashboard.class);
                                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                            startActivity(intent);
                                            Toast.makeText(customer_login.this, "OTP Verified Successfully", Toast.LENGTH_SHORT).show();
                                        }
                                        else{
                                            Toast.makeText(customer_login.this, "Enter The Correct OTP", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    }
                    else{
                        Toast.makeText(customer_login.this, "Please Check Your Internet Connection", Toast.LENGTH_SHORT).show();
                    }

                }
                else{
                    Toast.makeText(customer_login.this, "Please Enter Correct OTP", Toast.LENGTH_SHORT).show();
                }
            }
        });
        otpmove();

        resend_otp =findViewById(R.id.textresendotp);
        resend_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        "+91" + getIntent().getStringExtra("mobile"),
                        10,
                        TimeUnit.SECONDS,
                        customer_login.this,
                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {

                                Toast.makeText(customer_login.this, "Please check Your Internet Connection", Toast.LENGTH_SHORT).show();

                            }

                            @Override
                            public void onCodeSent(@NonNull String newdatabse_otp, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                getotpbackend=newdatabse_otp;
                                Toast.makeText(customer_login.this, "OTP Sent Successfully", Toast.LENGTH_SHORT).show();

                            }
                        }
                );
            }
        });


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
        otp4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()){
                    otp5.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        otp5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()){
                    otp6.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
