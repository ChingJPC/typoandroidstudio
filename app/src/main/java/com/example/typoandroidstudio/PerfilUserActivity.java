package com.example.typoandroidstudio;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.typoandroidstudio.infomascota.AddMascotaActivity;
import com.example.typoandroidstudio.infomascota.IndexMascotaActivity;
import com.example.typoandroidstudio.infomascota.ListMascotaActivity;
import com.example.typoandroidstudio.model.User;

public class PerfilUserActivity extends AppCompatActivity {

    TextView textViewUsername;
    public void next(View view){
        startActivity(new Intent(this, ListMascotaActivity.class));
    }
    public void next2(View view){
        startActivity(new Intent(this, IndexMascotaActivity.class));
    }
    public void logout(View view){
        startActivity(new Intent(this,LoginActivity.class));
        finish();
    }
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_user);

        User user = Datainfo.restLogin.getUser();

        textViewUsername = findViewById(R.id.user);
        textViewUsername.setText(user.getName());
    }
}