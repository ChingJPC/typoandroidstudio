package com.example.typoandroidstudio;

import static com.example.typoandroidstudio.R.id.pass;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class PerfilUserActivity extends AppCompatActivity {

    TextView textViewUsername;
    TextView textViewPassword;
    public void logout(View view){
        startActivity(new Intent(this,LoginActivity.class));
        finish();
    }
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_user);

        String username = getIntent().getStringExtra("username");
        String password = getIntent().getStringExtra("password");

        textViewUsername = findViewById(R.id.user);
        textViewPassword = findViewById(pass);

        textViewUsername.setText(username);
        textViewPassword.setText(password);
    }
}