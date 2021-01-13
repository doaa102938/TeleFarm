package com.example.telefarm2.ui.auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.telefarm2.MainActivity;
import com.example.telefarm2.R;
import com.example.telefarm2.RestorePasswordActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    //view
    private EditText _email ,_password ;
    private Button _login;
    FirebaseAuth FBauth;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //find view
        _email = findViewById(R.id.email_login);
        _password=findViewById(R.id.password_login);
        _login=findViewById(R.id.btn_login);
        FBauth=FirebaseAuth.getInstance();


        //intent to register activity 
        findViewById(R.id.CreateNewAccount).setOnClickListener(v -> startActivity (new Intent(LoginActivity.this,RegisterActivity.class)));
        //intent to restore password activity
        findViewById(R.id.forget_password).setOnClickListener(v -> startActivity (new Intent(LoginActivity.this, RestorePasswordActivity.class)));
        //intent to Main activity
        findViewById(R.id.Skip_login).setOnClickListener(v -> startActivity (new Intent(LoginActivity.this, MainActivity.class)));
          //Login
       _login.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String email = _email.getText().toString().trim();
               String pass = _password.getText().toString().trim();

               if (email.isEmpty()) {
                   _email.requestFocus();
                   ShowAlter("Email is Required");
                   return;
               }
               else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                   _email.requestFocus();
                   ShowAlter("Invalid Email Address");
                   return;
               }
               else if (pass.isEmpty()) {
                   _password.requestFocus();
                   ShowAlter("Password is Required");
                   return;
               }
               else if (pass.length() < 6) {
                   _password.requestFocus();
                   ShowAlter("Password must be >= 6 Digits ");
                   return;
               }

               FBauth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                   @Override
                   public void onComplete(@NonNull Task<AuthResult> task) {
                     if(  task.isSuccessful()){
                         Toast.makeText(LoginActivity.this,"Logged is Successful",Toast.LENGTH_SHORT).show();
                         startActivity(new Intent(LoginActivity.this, MainActivity.class));
                     }else {
                         ShowAlter(task.getException().getMessage());
                     }

                   }
               });

           }
       });



    }
    void ShowAlter (String msg){
        new AlertDialog.Builder(this)
                .setTitle("Error")
                .setMessage(msg)
                .setIcon(R.drawable.ic_error)
                .setPositiveButton("OK", null)
                .create().show();
    }


}