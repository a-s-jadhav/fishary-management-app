package com.example.fisharymanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class LoginActivity extends AppCompatActivity {

    EditText inputEmailLogin ,inputPasswordLogin;
    CheckBox showpassword;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button btnLogin = findViewById(R.id.btnLogin);
        TextView btnSignUp = findViewById(R.id.signUp);
        inputEmailLogin = findViewById(R.id.inputEmailLogin);
        inputPasswordLogin = findViewById(R.id.inputPasswordLogin);
        showpassword = findViewById(R.id.showpassword);
        showpassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    inputPasswordLogin.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                else{
                    inputPasswordLogin.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email= inputEmailLogin.getText().toString().trim();
                String password=inputPasswordLogin.getText().toString().trim();
                if(email.isEmpty())
                {
                    inputEmailLogin.setError("Email is empty");
                    inputEmailLogin.requestFocus();
                    return;
                }
                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
                {
                    inputEmailLogin.setError("Enter the valid email");
                    inputEmailLogin.requestFocus();
                    return;
                }
                if(password.isEmpty())
                {
                    inputPasswordLogin.setError("Password is empty");
                    inputPasswordLogin.requestFocus();
                    return;
                }
                if(password.length()<6)
                {
                    inputPasswordLogin.setError("Length of password is more than 6");
                    inputPasswordLogin.requestFocus();
                    return;
                }
                mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(task -> {
                    if(task.isSuccessful())
                    {
                        Toast.makeText(LoginActivity.this,"done bro done",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivity.this, Dashboard.class));
                    }
                    else
                    {
                        Toast.makeText(LoginActivity.this,"Please Check Your login Credentials",Toast.LENGTH_SHORT).show();
                    }

                });
            }
        });

    }

}