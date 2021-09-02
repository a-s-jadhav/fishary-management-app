package com.example.fisharymanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class LoginActivity extends AppCompatActivity {

    EditText inputEmailLogin ,inputPasswordLogin;
    TextView btnforgot;
    CheckBox showpassword;
    SharedPreferences sp;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button btnLogin = findViewById(R.id.btnLogin);
        TextView btnSignUp = findViewById(R.id.signUp);
        btnforgot = findViewById(R.id.forgottxt);
        inputEmailLogin = findViewById(R.id.inputEmailLogin);
        inputPasswordLogin = findViewById(R.id.inputPasswordLogin);
        showpassword = findViewById(R.id.showpassword);
        mAuth = FirebaseAuth.getInstance();
        sp = getSharedPreferences("login",MODE_PRIVATE);
        if(sp.contains("username") && sp.contains("password")){
            Toast.makeText(LoginActivity.this,"done bro done",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(LoginActivity.this, Dashboard.class));
        }
        showpassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    inputPasswordLogin.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    inputPasswordLogin.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
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
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putString("username",email);
                        editor.putString("password",password);
                        editor.commit();
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

        btnforgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText resetMail = new EditText(v.getContext());
                AlertDialog.Builder passwordResetDialog = new AlertDialog.Builder(v.getContext());
                passwordResetDialog.setTitle("Reset Password ?");
                passwordResetDialog.setMessage("Enter Your Email To Received Reset Link.");
                passwordResetDialog.setView(resetMail);
                passwordResetDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //extract the email and send reset link
                        String mail = resetMail.getText().toString();
                        mAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(LoginActivity.this,"Reset Link Sent To Your Email",Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(LoginActivity.this,"Error ! Reset Link is Not Sent"+e.getMessage(),Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });

                passwordResetDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //close the dialog
                    }
                });
                passwordResetDialog.create().show();
            }
        });

    }

}