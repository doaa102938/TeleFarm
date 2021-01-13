package com.example.telefarm2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.telefarm2.ui.auth.LoginActivity;
import com.example.telefarm2.ui.auth.RegisterActivity;

public class RestorePasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restore_password);

        //intent to Login activity
        findViewById(R.id.back_restore_password).setOnClickListener(v -> startActivity(new Intent(RestorePasswordActivity.this,LoginActivity.class)));
    }
}