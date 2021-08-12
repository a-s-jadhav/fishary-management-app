package com.example.fisharymanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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


        //Name Validation
        inputName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String name = inputName.getText().toString();
                if(!name.isEmpty() &&  validateName(inputName.getText().toString())) {
                    btnRegister.setEnabled(true);
                }
                else {
                    btnRegister.setEnabled(false);
                    inputName.setError("Invalid Name");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        //Email Id validation
        inputEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(validateEmailId(inputEmail.getText().toString())){
                    btnRegister.setEnabled(true);
                }
                else {
                    btnRegister.setEnabled(false);
                    inputEmail.setError("Invalid Email Id");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        //Mobile number validation Logic
        inputMobile.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(validateMobileNo(inputMobile.getText().toString())){
                    btnRegister.setEnabled(true);
                }
                else {
                    btnRegister.setEnabled(false);
                    inputMobile.setError("Invalid Mobile No");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        // Password Validation
        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(validatePassword(password.getText().toString())){
                    btnRegister.setEnabled(true);
                }
                else {
                    btnRegister.setEnabled(false);
                    password.setError("Invalid Password");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        //checking password and confirm pasword is same
        inputConfirmPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String pass = password.getText().toString();
                String confirmpass = inputConfirmPassword.getText().toString();
                if(pass.equals(confirmpass)){
                    btnRegister.setEnabled(true);
                }
                else{
                    btnRegister.setEnabled(false);
                    inputConfirmPassword.setError("Password Mismatch");
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

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
                String enterName = inputName.getText().toString().trim();
                if(enterName.isEmpty()){
                    inputName.setError("Name is empty");
                    inputName.requestFocus();
                    return;
                }

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
                            openSignInPage();

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

    //Validate name
    boolean validateName(String inputname){
        Pattern p = Pattern.compile("[a-zA-Z]{3,}");
        Matcher m = p.matcher(inputname);
        return m.matches();
    }

    //Validate mobile number
    boolean validateMobileNo(String input){
        Pattern p = Pattern.compile("[6-9][0-9]{9}");
        Matcher m = p.matcher(input);
        return m.matches();
    }

    //Validate Email ID
    boolean validateEmailId(String inputemail){
        if(!inputemail.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(inputemail).matches()){
            return true;
        }
        else {
            return false;
        }
    }

    //Validate Password
    boolean validatePassword(String inputpassword){
        Pattern p = Pattern.compile("^" +
                              "(?=.*[0-9])" +     // at least 1 digit
                              "(?=.*[a-zA-z])" +  // at least 1 letter
                              "(?=.*[@$!%#?&])" + // ar least 1 special character
                              "(?=\\S+$)" +       // no white spaces
                              ".{6,}" +           // at least 6 characters
                               "$");              // end of the string
        Matcher m = p.matcher(inputpassword);
        return m.matches();
    }

    // Redirecting sign Up page to sing in Page
    public void openSignInPage(){
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
    }
}