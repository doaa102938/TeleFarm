package com.example.telefarm2.ui.auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.telefarm2.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    //view
    private EditText edName, edPhone, edEmail, edPassword;
    //firebase
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firestore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //findView
        edName = findViewById(R.id.name_register);
        edPhone = findViewById(R.id.phone_register);
        edEmail = findViewById(R.id.email_register);
        edPassword = findViewById(R.id.password_register);
        //firebase
        firebaseAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        //on click to register new user
        findViewById(R.id.register).setOnClickListener(v -> {
            ValidationDate();

            // on click to finish this activity
            findViewById(R.id.back_register).setOnClickListener(v1 -> finish());

        });
    }

    private void ValidationDate() {
        String name = edName.getText().toString().trim();
        String phone = edPhone.getText().toString().trim();
        String email = edEmail.getText().toString().trim();
        String pass = edPassword.getText().toString().trim();

        if (email.isEmpty()) {
            edEmail.requestFocus();
            ShowAlter("Email is Required");
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            edEmail.requestFocus();
            ShowAlter("Invalid Email Address");
            return;
        }
        if (pass.isEmpty()) {
            edPassword.requestFocus();
            ShowAlter("Password is Required");
            return;
        }
        if (pass.length() < 6) {
            edPassword.requestFocus();
            ShowAlter("Password must be a Digit ");
            return;
        }
        if (name.isEmpty()) {
            edName.requestFocus();
            ShowAlter("Name is Required");
            return;
        }
        if (phone.isEmpty()) {
            edPhone.requestFocus();
            ShowAlter("Phone is Required");
            return;
        }
        if (phone.length() < 11) {
            edPhone.requestFocus();
            ShowAlter("Invaild Phone Number");
            return;
        }
        SignUpWithFirebase(email, pass);
    }

    private void SignUpWithFirebase(String email, String pass) {
        firebaseAuth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        SaveUserData();
                    } else {
                        ShowAlter(task.getException().getMessage());
                    }

                });

    }

    private void SaveUserData() {


            String userID = firebaseAuth.getCurrentUser().getUid();
            Map<String, Object> user = new HashMap<>();
            user.put("email", edEmail.getText().toString().trim());
            user.put("password", edPassword.getText().toString().trim());
            user.put("phone", edPhone.getText().toString().trim());
            user.put("name", edName.getText().toString().trim());
            firestore.collection("Users")
                    .add(user)
                    .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentReference> task) {

                        }
                    });



        }

        //12*12
        void ShowAlter (String msg){
            new AlertDialog.Builder(this)
                    .setTitle("Error")
                    .setMessage(msg)
                    .setIcon(R.drawable.ic_error)
                    .setPositiveButton("OK", null)
                    .create().show();
        }
    }


