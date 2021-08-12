package com.example.fisharymanagement;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgetPasswordActivity extends AppCompatActivity {

    Button forgotbtn;
    EditText txtEmail;
    String email;
    FirebaseAuth auth;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        auth = FirebaseAuth.getInstance();
        txtEmail = findViewById(R.id.inputEmailLogin);
        forgotbtn = findViewById(R.id.button);

        forgotbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateData();
            }
        });

    }
    private void validateData(){
        email = txtEmail.getText().toString();
        if(email.isEmpty()){
            txtEmail.setError("Required");
        }
        else {
            forgotpassword();
        }
    }

    private void forgotpassword(){
        auth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(ForgetPasswordActivity.this,"Check Your Email",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(ForgetPasswordActivity.this,LoginActivity.class));
                            finish();
                        }
                        else {
                            Toast.makeText(ForgetPasswordActivity.this,"Error:"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
