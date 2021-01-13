package com.example.telefarm2.ui.auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.telefarm2.MainActivity;
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
    //button
    private Button register ;

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

        //view button
        register=findViewById(R.id.register);
        //firebase
        firebaseAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        //on click to register new user
        findViewById(R.id.register).setOnClickListener(v -> {
            ValidationDate();

            // on click to finish this activity
            findViewById(R.id.back_register).setOnClickListener(v1-> finish());
            //
            findViewById(R.id.have_account).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                }
            });

            //
            findViewById(R.id.terms_conditions).setOnClickListener(v12 -> {
                openPrivacy();
            });

        });
    }

    private void openPrivacy() {
        Uri uri = Uri.parse("https://tele-farm.flycricket.io/privacy.html\n" +
                "        https://tele-farm.flycricket.io/terms.html"); // missing 'http://' will cause crashed
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);


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
         else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            edEmail.requestFocus();
            ShowAlter("Invalid Email Address");
            return;
        }
         else if (pass.isEmpty()) {
            edPassword.requestFocus();
            ShowAlter("Password is Required");
            return;
        }
        else if (pass.length() < 6) {
            edPassword.requestFocus();
            ShowAlter("Password must be >= 6 Digits ");
            return;
        }
         else if (name.isEmpty()) {
            edName.requestFocus();
            ShowAlter("Name is Required");
            return;
        }
         else if (phone.isEmpty()) {
            edPhone.requestFocus();
            ShowAlter("Phone is Required");
            return;
        }
         else if (phone.length() < 11) {
            edPhone.requestFocus();
            ShowAlter(" Phone Number must be = 11 Digits");
            return;
        }


        SignUpWithFirebase(email, pass);
    }




    private void SignUpWithFirebase(String email, String pass) {
        firebaseAuth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(RegisterActivity.this,"User Created",Toast.LENGTH_SHORT).show();
                        SaveUserData();
                        startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                    } else {
                        ShowAlter(task.getException().getMessage());
                    }

                });

    }


    private void SaveUserData() {


     if (firebaseAuth.getCurrentUser()!= null) {


         String userID = firebaseAuth.getCurrentUser().getUid();
         Map<String, Object> user = new HashMap<>();
         user.put("email", edEmail.getText().toString().trim());
         user.put("password", edPassword.getText().toString().trim());
         user.put("phone", edPhone.getText().toString().trim());
         user.put("name", edName.getText().toString().trim());
         user.put("id", userID);
         user.put("image","null");

         firestore.collection("Users")
                 .document(userID)
                 .set(user)
                 .addOnCompleteListener(new OnCompleteListener<Void>() {
                     @Override
                     public void onComplete(@NonNull Task<Void> task) {
                         if (task.isSuccessful()) {

                         }  else {
                             ShowAlter("Error "+ task.getException().getMessage());
                         }
                     }
                 });




     }


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



