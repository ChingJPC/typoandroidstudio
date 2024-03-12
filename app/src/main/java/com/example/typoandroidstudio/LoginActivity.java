package com.example.typoandroidstudio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import com.example.typoandroidstudio.model.RestLogin;
import com.example.typoandroidstudio.network.LoginAPIS.LoginAPIClient;
import com.example.typoandroidstudio.network.LoginAPIS.LoginAPIService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private LoginAPIService service;
    public void next(View view){
        startActivity(new Intent(this, RegisterActivity.class));
        finish();
    }
    public void login(View view) {
        if (view.getId()==R.id.btnlogin) {

            EditText caja1 = findViewById(R.id.username);
            EditText caja2 = findViewById(R.id.password);

            String email = caja1.getText().toString();
            String password = caja2.getText().toString();

            if (email==null || email.length()==0) {
                return;
            }
            if (password==null || password.length()==0) {
                return;
            }
            service.login(email, password).enqueue(new Callback<RestLogin>() {
                @Override
                public void onResponse(Call<RestLogin> call, Response<RestLogin> response) {
                    if (response.isSuccessful()){
                        Datainfo.restLogin=response.body();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(LoginActivity.this, "Error al Iniciar Sesion", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<RestLogin> call, Throwable t) {
                    Toast.makeText(LoginActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("msg", "onCreate");

        service= LoginAPIClient.getLoginService();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }
}