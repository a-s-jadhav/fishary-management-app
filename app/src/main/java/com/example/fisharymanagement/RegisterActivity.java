package com.example.fisharymanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    EditText inputName, inputEmail, inputMobile, password ,inputConfirmPassword;
    Button btnRegister;
    CheckBox showPassword;
    DatabaseReference referenceFirebase;
    Member member, memberTest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Firebase Database Connectivity
        referenceFirebase = FirebaseDatabase.getInstance("https://fishary-management-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference().child("Members");
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
                Toast.makeText(RegisterActivity.this,"Registeration Done",Toast.LENGTH_LONG).show();
            }
        });
        //Already Having Account Logic
        btnHavingAcc.setOnClickListener(v -> startActivity(new Intent(RegisterActivity.this,LoginActivity.class)));
    }
}