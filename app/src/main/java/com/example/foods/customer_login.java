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

public class customer_login extends AppCompatActivity {
    FirebaseAuth mAuth;
    FirebaseUser user;
    TextView pass_forget;
    EditText et_email,et_pass;
    Button btn_shi,btn_reg2;;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_login);
        Button btn_reg2;
        btn_reg2=findViewById(R.id.btn_reg2);
        btn_reg2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(customer_login.this,CustomerRegistration.class);
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
        pass_forget=findViewById(R.id.pass_forget);
        pass_forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_email=findViewById(R.id.mail_customer);
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

        btn_shi=findViewById(R.id.btn_login2);
        btn_shi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_email=findViewById(R.id.mail_customer);
                et_pass=findViewById(R.id.pass_customer);
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
                        Intent intent = new Intent(customer_login.this, customer_dashboard.class);
                        startActivity(intent);

                    } else {
                        Toast.makeText(customer_login.this, "Login failed", Toast.LENGTH_SHORT).show();
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