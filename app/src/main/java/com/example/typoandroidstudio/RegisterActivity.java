package com.example.typoandroidstudio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.typoandroidstudio.infomascota.AddMascotaActivity;
import com.example.typoandroidstudio.model.RestLogin;
import com.example.typoandroidstudio.model.User;
import com.example.typoandroidstudio.network.LoginAPIS.LoginAPIClient;
import com.example.typoandroidstudio.network.LoginAPIS.LoginAPIService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private LoginAPIService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        service= LoginAPIClient.getLoginService();
    }


    public void executeR(View view) {

        if (view.getId()==R.id.btnregister) {

            EditText caja1 = findViewById(R.id.txtname);
            EditText caja2 = findViewById(R.id.txtapellido);
            EditText caja3 = findViewById(R.id.txttelefono);
            EditText caja4 = findViewById(R.id.txtfecha);
            EditText caja5 = findViewById(R.id.txtemail);
            EditText caja6 = findViewById(R.id.txtpassword);


            String name = caja1.getText().toString();
            String apellido = caja2.getText().toString();
            String telefono = caja3.getText().toString();
            String fecha = caja4.getText().toString();
            String email = caja5.getText().toString();
            String password = caja6.getText().toString();


            if (name==null || name.length()==0) {
                return;
            }
            if (name==null || apellido.length()==0) {
                return;
            }
            if (name==null || telefono.length()==0) {
                return;
            }
            if (name==null || fecha.length()==0) {
                return;
            }
            if (email==null || email.length()==0) {
                return;
            }
            if (password==null || password.length()==0) {
                return;
            }



            Log.d("RegisterActivity", "Before Retrofit call");

            service.register(name,apellido,telefono,fecha, email, password).enqueue(new Callback<RestLogin>() {
                @Override
                public void onResponse(Call<RestLogin> call, Response<RestLogin> response) {
                    if (response.isSuccessful()){
                        Datainfo.restLogin=response.body();
                        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(RegisterActivity.this, "Error al Registrarse", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<RestLogin> call, Throwable t) {
                Log.e("Typo",t.getMessage());
                }
            });
            Log.d("RegisterActivity", "After Retrofit call");



        }

    }


    public void goLogin(View view) {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

}