package com.example.foods;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.FirebaseUser;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class CustomerRegistration <FirebaseUser> extends AppCompatActivity {
    String email,pass;
    FirebaseAuth mAuth;
    FirebaseUser user;
    EditText et_email,et_pass;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_registration);
        mAuth=FirebaseAuth.getInstance();
        user= (FirebaseUser) mAuth.getCurrentUser();

        button=findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_email=findViewById(R.id.custom_mail);
                et_pass=findViewById(R.id.e_customer_pass_id);
                email=et_email.getText().toString();
                pass=et_pass.getText().toString();
                registerNewUser(email,pass);
            }
        });
    }
    private void  registerNewUser(String email,String password) {
        if(!TextUtils.isEmpty(email)&&!TextUtils.isEmpty(password)) {
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), "Registration Successful", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(CustomerRegistration.this, customer_dashboard.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(CustomerRegistration.this, "Registration Unsuccessful", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        }
        else{
            et_email.setError("Required Field");
            et_pass.setError("Required Field");
        }
    }
}