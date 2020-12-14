package com.example.telefarm2.ui.auth;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;

import com.example.telefarm2.R;

public class LoginActivity extends AppCompatActivity {
    //view
    private EditText _email ,_password ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //find view
        _email = findViewById(R.id.email_login);
        _password=findViewById(R.id.password_login);

        //intent to register activity
        findViewById(R.id.CreateNewAccount).setOnClickListener(v -> startActivity (new Intent(LoginActivity.this,RegisterActivity.class)));
        //OnClick to Login
        findViewById(R.id.btn_login).setOnClickListener(v -> {
            validationData();

        });
    }

    private void validationData() {
        String email =_email.getText().toString().trim();
        String pass =_password.getText().toString().trim();
        if (email.isEmpty()){
            _email.requestFocus();
            ShowAlter("Email is Required");
            return;
        }
        if ( ! Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            _email.requestFocus();
            ShowAlter("Invalid Email Address");
            return;
        }
        if (pass.isEmpty()) {
            _password.requestFocus();
            ShowAlter("Password is Required");
            return;
        }
        if (pass.length()<6)
            _password.requestFocus();
        ShowAlter("Password must be a Digit");
        return;
    }
    void  ShowAlter (String msg){
        new AlertDialog.Builder(this)
                .setTitle("Error")
                .setMessage("msg")
                .setIcon(R.drawable.ic_error)
                .setPositiveButton("Ok",null)
                .create().show();
    }
}