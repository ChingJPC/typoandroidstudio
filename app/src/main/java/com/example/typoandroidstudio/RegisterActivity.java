package com.example.typoandroidstudio;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
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
        service = LoginAPIClient.getLoginService(); // Inicializa el servicio de registro
    }

    // Método para manejar el registro del usuario
    public void executeR(View view) {
        if (view.getId() == R.id.btnregister) {
            // Obtener referencias a los campos de texto
            EditText caja1 = findViewById(R.id.txtname);
            EditText caja2 = findViewById(R.id.txtapellido);
            EditText caja3 = findViewById(R.id.txttelefono);
            EditText caja4 = findViewById(R.id.txtfecha);
            EditText caja5 = findViewById(R.id.txtemail);
            EditText caja6 = findViewById(R.id.txtpassword);

            // Obtener los valores de los campos de texto
            String name = caja1.getText().toString();
            String apellido = caja2.getText().toString();
            String telefono = caja3.getText().toString();
            String fecha = caja4.getText().toString();
            String email = caja5.getText().toString();
            String password = caja6.getText().toString();

            // Validar que todos los campos estén llenos
            if (name == null || name.length() == 0 ||
                    apellido == null || apellido.length() == 0 ||
                    telefono == null || telefono.length() == 0 ||
                    fecha == null || fecha.length() == 0 ||
                    email == null || email.length() == 0 ||
                    password == null || password.length() == 0) {
                return;
            }

            // Validar el formato del correo electrónico
            if (!validarCorreo(email)) {
                mostrarAlerta("Correo electrónico inválido");
                return;
            }

            // Realizar la llamada al servicio de registro
            Log.d("RegisterActivity", "Before Retrofit call");
            service.register(name, apellido, telefono, fecha, email, password).enqueue(new Callback<RestLogin>() {
                @Override
                public void onResponse(Call<RestLogin> call, Response<RestLogin> response) {
                    if (response.isSuccessful()) {
                        // Registro exitoso, redirigir a la pantalla principal
                        Datainfo.restLogin = response.body();
                        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                        startActivity(intent);
                    } else {
                        // Error en el registro, mostrar mensaje de error
                        Toast.makeText(RegisterActivity.this, "Error al Registrarse", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<RestLogin> call, Throwable t) {
                    // Error en la llamada al servicio, mostrar mensaje de error
                    Log.e("Typo", t.getMessage());
                }
            });
            Log.d("RegisterActivity", "After Retrofit call");
        }
    }

    // Método para manejar el botón de ir a la pantalla de login
    public void goLogin(View view) {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    // Método para validar el formato del correo electrónico
    private boolean validarCorreo(String correo) {
        return correo.matches("[a-zA-Z0-9._%+-]+@(gmail|hotmail|outlook)\\.(com)");
    }

    // Método para mostrar un AlertDialog con un mensaje
    private void mostrarAlerta(String mensaje) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(mensaje)
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Cierra el diálogo
                        dialog.dismiss();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
