package com.example.fisharymanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Patterns;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    EditText inputName, inputEmail, inputMobile, password ,inputConfirmPassword;
    Button btnRegister;
    CheckBox showPassword;
    DatabaseReference referenceFirebase;
    FirebaseAuth mAuth;
    Member member, memberTest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        String urlIMP = "https://fishary-management-default-rtdb.asia-southeast1.firebasedatabase.app/";
        //Firebase Database Connectivity
        referenceFirebase = FirebaseDatabase.getInstance("https://fishary-management-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference().child("Members");
        mAuth=FirebaseAuth.getInstance();
        // Write a message to the database
        //please dont delete it , this is for testing
        //FirebaseDatabase database = FirebaseDatabase.getInstance("https://fishary-management-default-rtdb.asia-southeast1.firebasedatabase.app/");
        //DatabaseReference referenceFirebase = database.getReference("message");
        //TextView for Already Having Account
        TextView btnHavingAcc = findViewById(R.id.alreadyHaveAccount);
        //Button for Register
        btnRegister = findViewById(R.id.btnRegister);
        //EditText for Name
        inputName = findViewById(R.id.inputName);
        //EditText for Email
        inputEmail = findViewById(R.id.inputEmail);
        //EditText for Mobile
        inputMobile = findViewById(R.id.inputMobileNo);
        //EditText for Password
        password = findViewById(R.id.inputPassword);
        //EditText for Re-enter password
        inputConfirmPassword = findViewById(R.id.inputConfirmPassword);
        //CheckBox for Show password
        showPassword= findViewById(R.id.showpassword2);
        //Member Variable
        member = new Member();

        //Show Password Logic
        showPassword.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked){
                password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                inputConfirmPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }
            else{
                password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                inputConfirmPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
        });

        btnRegister.setOnClickListener(v -> {
            if(inputName.getText().toString().isEmpty() || inputEmail.getText().toString().isEmpty() || inputMobile.getText().toString().isEmpty() || password.getText().toString().isEmpty() || inputConfirmPassword.getText().toString().isEmpty()){
                Toast.makeText(RegisterActivity.this,"Please Complete the all fields",Toast.LENGTH_LONG).show();
            }else {
                member.setName(inputName.getText().toString().trim());
                member.setEmail(inputEmail.getText().toString().trim());
                member.setMobile(inputMobile.getText().toString().trim());
                member.setPassword(password.getText().toString().trim());
                referenceFirebase.push().setValue(member);
                String enterEmail = inputEmail.getText().toString().trim();
                String enterPass = password.getText().toString().trim();
                if (enterEmail.isEmpty()) {
                    inputEmail.setError("Email is empty");
                    inputEmail.requestFocus();
                    return;
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(enterEmail).matches()) {
                    inputEmail.setError("Enter the valid email address");
                    inputEmail.requestFocus();
                    return;
                }
                if (enterPass.isEmpty()) {
                    password.setError("Enter the password");
                    password.requestFocus();
                    return;
                }
                if (enterPass.length() < 6) {
                    password.setError("Length of the password should be more than 6");
                    password.requestFocus();
                    return;
                }
                mAuth.createUserWithEmailAndPassword(enterEmail, enterPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(RegisterActivity.this, "You are successfully Registered", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(RegisterActivity.this, "You are not Registered! Try again", Toast.LENGTH_SHORT).show();
                        }
                    }

                });
            }
        });
        //Already Having Account Logic
        btnHavingAcc.setOnClickListener(v -> startActivity(new Intent(RegisterActivity.this,LoginActivity.class)));
    }
}