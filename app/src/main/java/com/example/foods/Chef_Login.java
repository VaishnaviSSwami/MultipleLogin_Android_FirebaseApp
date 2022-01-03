package com.example.foods;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Chef_Login extends AppCompatActivity {
    FirebaseAuth mAuth;
    FirebaseUser user;
    TextView tv_forget;
    EditText et_email,et_pass;
    Button btn_signin,btn_reg;;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chef_login);
        Button btn_reg;
        btn_reg=findViewById(R.id.btn_reg);
        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Chef_Login.this,ChefRegistration.class);
                startActivity(i);
            }
        });
        mAuth=FirebaseAuth.getInstance();
        user=mAuth.getCurrentUser();

        view();
    }
    public void shareDetails(String email){
        ShareData shareData=new ShareData(email);
    }
    public void view(){
        tv_forget=findViewById(R.id.tv_forget);
        tv_forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_email=findViewById(R.id.et_chef_mail);
                String email;
                email=et_email.getText().toString();
                shareDetails(email);
                sendForgotPassLink(email);
            }

            private void sendForgotPassLink(String email) {
                if (!TextUtils.isEmpty(email)) {
                    mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(getApplicationContext(), "Reset Password Link Sent to: "+email, Toast.LENGTH_LONG).show();
                        }
                    });
                }
                else {
                    et_email.setError("Required Field");
                }
            }
        });

        btn_signin=findViewById(R.id.btn_login);
        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_email=findViewById(R.id.et_chef_mail);
                et_pass=findViewById(R.id.et_chef_pass);
                String email,pass;
                email=et_email.getText().toString();
                pass=et_pass.getText().toString();
                login(email,pass);
            }
        });
    }
    public void login(String email, String password) {
        if(!TextUtils.isEmpty(email)&&!TextUtils.isEmpty(password)) {
            Log.d("test", email + " " + password);
            mAuth = FirebaseAuth.getInstance();
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), "Login successful", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(Chef_Login.this, Chef_Dashboard.class);
                        startActivity(intent);

                    } else {
                        Toast.makeText(Chef_Login.this, "Login failed", Toast.LENGTH_SHORT).show();
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
