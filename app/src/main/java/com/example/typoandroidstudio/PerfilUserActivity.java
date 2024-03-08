package com.example.typoandroidstudio;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.typoandroidstudio.infomascota.IndexMascotaActivity;
import com.example.typoandroidstudio.infomascota.ListMascotaActivity;
import com.example.typoandroidstudio.model.User;

public class PerfilUserActivity extends AppCompatActivity {

    TextView textViewUsername;
    TextView textViewEmail;
    public void next(View view){
        startActivity(new Intent(this, ListMascotaActivity.class));
    }
    public void next2(View view){
        startActivity(new Intent(this, IndexMascotaActivity.class));
    }
    public void logout(View view){
        finish();
    }
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        User user = Datainfo.restLogin.getUser();

        textViewUsername = findViewById(R.id.user);
        //textViewEmail = findViewById(R.id.email);
        textViewUsername.setText(user.getName());
        //textViewEmail.setText(user.getEmail());

    }
}